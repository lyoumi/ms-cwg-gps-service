package com.cwd.tg.gps.services;

import com.cwd.tg.gps.data.character.GameCharacter;

import reactor.core.publisher.Flux;

public interface GameCharacterService {
    Flux<GameCharacter> getAllGameCharacters();
}
