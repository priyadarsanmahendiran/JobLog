package com.joblog.repositories.interfaces;

import com.joblog.models.entities.Worklog;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface IWorkLogRepository extends CrudRepository<Worklog, UUID> {}
