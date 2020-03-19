package com.cwd.tg.gps.services.impl;

import com.cwd.tg.gps.client.CoreWebClient;
import com.cwd.tg.gps.data.character.GameCharacter;
import com.cwd.tg.gps.services.GameCharacterService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class GameCharacterServiceImpl implements GameCharacterService {

    private final CoreWebClient coreWebClient;

    @Override
    public Flux<GameCharacter> getAllGameCharacters() {
        return coreWebClient.getAllGameCharacters();
    }
}
