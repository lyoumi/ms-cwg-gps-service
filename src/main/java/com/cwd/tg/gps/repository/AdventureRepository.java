package com.cwd.tg.gps.repository;

import com.cwd.tg.gps.entity.AdventureEntity;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AdventureRepository extends ReactiveMongoRepository<AdventureEntity, String> {
}
