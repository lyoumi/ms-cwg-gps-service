package com.cwd.tg.gps.processors.impl;

import com.cwd.tg.gps.client.CoreWebClient;
import com.cwd.tg.gps.data.character.GameCharacter;
import com.cwd.tg.gps.data.events.adventure.Adventure;
import com.cwd.tg.gps.data.events.adventure.AdventureStatus;
import com.cwd.tg.gps.data.events.awards.Awards;
import com.cwd.tg.gps.processors.EventProcessor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Component("adventureEventProcessor")
@AllArgsConstructor
public class AdventureEventProcessor implements EventProcessor {

    private static final String FINISH_ADVENTURE_EVENT = "I'm done, man!";

    private final CoreWebClient coreWebClient;

    @Override
    public void processEvent(GameCharacter gameCharacter) {
        processAdventure(gameCharacter);
    }

    private void processAdventure(GameCharacter gameCharacter) {
        if (!gameCharacter.isFighting()) {
            coreWebClient.getActiveAdventureByCharacterId(gameCharacter.getId())
                    .doOnSuccess(adventure -> {
                        var currentEventStep = adventure.getStep();
                        if (currentEventStep < adventure.getAdventureEvents().size()) {
                            gameCharacter.setCurrentAction(adventure.getAdventureEvents().get(currentEventStep));
                            adventure.setStep(++currentEventStep);
                        } else {
                            finishAdventure(gameCharacter, adventure);
                        }
                    })
                    .subscribe(adventure -> coreWebClient.updateAdventure(adventure)
                                    .subscribeOn(Schedulers.parallel())
                                    .subscribe(),
                            exception -> log.error("Something went wrong", exception),
                            () -> coreWebClient.saveGameCharacter(gameCharacter)
                                    .subscribeOn(Schedulers.parallel())
                                    .subscribe());
        }
    }

    private void finishAdventure(GameCharacter gameCharacter, Adventure adventure) {
        gameCharacter.setInAdventure(false);
        gameCharacter.setCurrentAction(FINISH_ADVENTURE_EVENT);
        adventure.setStatus(AdventureStatus.CLOSED);

        coreWebClient.getAwardsById(adventure.getAwardsId())
                .subscribeOn(Schedulers.parallel())
                .doOnSuccess(awards -> {
                    gameCharacter.getProgress()
                            .setCurrentExp(gameCharacter.getProgress().getCurrentExp() + awards.getExperience());
                    gameCharacter.getGameInventory()
                            .setGold(gameCharacter.getGameInventory().getGold() + awards.getGold());
                })
                .map(Awards::getId)
                .subscribe(id -> coreWebClient.deleteAwards(id)
                        .subscribeOn(Schedulers.parallel())
                        .subscribe());
    }
}
