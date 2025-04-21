package com.joblog.repositories.interfaces;

import com.joblog.models.entities.Worklog;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface IWorkLogRepository extends CrudRepository<Worklog, UUID> {

  Optional<List<Worklog>> findByUserId(UUID userId);

  Worklog findByUserIdAndLogDate(UUID userId, String logDate);

  void deleteByUserIdAndLogDate(UUID userId, String logDate);
}
