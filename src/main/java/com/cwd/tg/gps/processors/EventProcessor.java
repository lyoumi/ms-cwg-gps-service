package com.cwd.tg.gps.processors;

import com.cwd.tg.gps.data.character.GameCharacter;

public interface EventProcessor {
    void processEvent(GameCharacter gameCharacter);
}
