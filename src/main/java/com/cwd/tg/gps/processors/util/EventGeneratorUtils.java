package com.cwd.tg.gps.processors.util;

import com.cwd.tg.gps.data.character.GameCharacter;
import com.cwd.tg.gps.data.events.adventure.Adventure;
import com.cwd.tg.gps.data.events.battle.Monster;

import reactor.core.publisher.Mono;

public interface EventGeneratorUtils {

    Mono<Adventure> generateAdventure(String characterId, String awardsId);

    Monster generateMonster(GameCharacter gameCharacter);
}
