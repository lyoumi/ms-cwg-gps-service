package com.cwd.tg.gps.data.events.battle;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Data
@NoArgsConstructor
public class Monster {
    private String id;
    private long hitPoints;
    private long attack;
    private MonsterType monsterType;

    private DateTime createdAt;
    private DateTime lastModifiedAt;

    public Monster(long hitPoints, long attack, MonsterType monsterType) {
        this.hitPoints = hitPoints;
        this.attack = attack;
        this.monsterType = monsterType;
    }
}
