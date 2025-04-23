package com.joblog.repositories.interfaces;

import com.joblog.models.entities.StandupSummary;
import org.springframework.data.repository.CrudRepository;

public interface IStandupRepository extends CrudRepository<StandupSummary, Long> {}
