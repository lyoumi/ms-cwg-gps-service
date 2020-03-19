package com.cwd.tg.gps.processors.impl;

import com.cwd.tg.gps.data.character.GameCharacter;
import com.cwd.tg.gps.processors.EventProcessor;

import org.springframework.stereotype.Component;

@Component("healingEventProcessor")
public class HealingEventProcessor implements EventProcessor {
    @Override
    public void processEvent(GameCharacter gameCharacter) {

    }
}
