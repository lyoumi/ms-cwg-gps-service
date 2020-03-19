package com.cwd.tg.gps.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class AdventureEntity {

    private String description;
    private List<String> events;
}
