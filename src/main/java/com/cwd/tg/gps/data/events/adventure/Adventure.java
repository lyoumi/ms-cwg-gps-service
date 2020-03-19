package com.cwd.tg.gps.data.events.adventure;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.List;

@Data
@NoArgsConstructor
public class Adventure {
    private String id;
    private String characterId;
    private String description;
    private String awardsId;
    private AdventureStatus status;
    private List<String> adventureEvents;
    private int step;

    private DateTime createdAt;
    private DateTime lastModifiedAt;

    public Adventure(String characterId, String description, String awardsId,
            AdventureStatus status, List<String> adventureEvents, int step) {
        this.characterId = characterId;
        this.description = description;
        this.awardsId = awardsId;
        this.status = status;
        this.adventureEvents = adventureEvents;
        this.step = step;
    }
}
