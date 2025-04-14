package com.joblog.repositories.interfaces;

import com.joblog.models.entities.Worklog;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IWorkLogRepository extends CrudRepository<Worklog, UUID> {
}
