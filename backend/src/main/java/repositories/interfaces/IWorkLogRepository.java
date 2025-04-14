package repositories.interfaces;

import models.entities.Worklog;
import org.springframework.data.repository.CrudRepository;

public interface IWorkLogRepository extends CrudRepository<Worklog, Long> {
}
