package com.cwd.tg.gps.data.events.awards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Awards {
    private String id;
    private long experience;
    private long gold;
}
