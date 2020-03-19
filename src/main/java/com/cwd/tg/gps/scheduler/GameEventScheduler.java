package com.cwd.tg.gps.scheduler;

import com.cwd.tg.gps.processors.CommonEventProcessor;
import com.cwd.tg.gps.services.GameCharacterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class GameEventScheduler {

    @Value("${window.size}")
    private int windowSize;
    @Value("${window.delay}")
    private int windowDelayTime;

    private final GameCharacterService gameCharacterService;
    private final CommonEventProcessor commonEventProcessor;

    @Scheduled(fixedDelayString = "${scheduler.delay}")
    public void scheduleGameEvent() {
        gameCharacterService.getAllGameCharacters()
                .subscribeOn(Schedulers.elastic())
                .doOnError(
                        throwable -> log.error("Something went wrong. Error during character processing.", throwable))
                .window(windowSize)
                .delayElements(Duration.ofSeconds(windowDelayTime), Schedulers.elastic())
                .subscribe(gcm -> gcm.subscribe(commonEventProcessor::processCharacterEvents,
                        throwable -> log.error("Something went wrong", throwable)),
                        throwable -> log.error("Something went wrong", throwable));
    }
}
