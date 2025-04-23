package com.joblog.repositories.interfaces;

import com.joblog.models.entities.Worklog;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface IWorkLogRepository extends CrudRepository<Worklog, UUID> {

  Optional<List<Worklog>> findByUserId(UUID userId);

  Optional<List<Worklog>> findByUserIdAndLogDateBetween(
      UUID userId, LocalDate startDate, LocalDate endDate);

  Optional<Worklog> findByUserIdAndLogDate(UUID userId, LocalDate logDate);
}
