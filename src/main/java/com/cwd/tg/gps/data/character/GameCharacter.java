package com.cwd.tg.gps.data.character;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class GameCharacter {
    private String id;
    private String name;
    private boolean inAdventure;
    private boolean isFighting;
    private boolean isResting;
    private String currentAction;
    private CharacterProgress progress;
    private CharacterStats stats;
    private GameInventory gameInventory;
    private String userId;

    private DateTime createdAt;
    private DateTime lastModifiedAt;

    public boolean hasEvent() {
        return inAdventure || isFighting || isResting;
    }
}
