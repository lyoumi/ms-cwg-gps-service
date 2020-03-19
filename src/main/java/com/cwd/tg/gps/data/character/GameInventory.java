package com.cwd.tg.gps.data.character;

import lombok.Data;

import java.util.Map;

@Data
public class GameInventory {
    private long gold;
    private long healingHitPointItems;
    private long healingManaPointItems;
    private Map<EquipmentSlot, EquipmentItem> equipment;
}
