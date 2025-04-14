package repositories.impl;

import models.entities.Worklog;
import org.springframework.stereotype.Repository;
import repositories.interfaces.IWorkLogRepository;

import java.util.Optional;


@Repository
public class WorkLogRepository implements IWorkLogRepository {
    @Override
    public <S extends Worklog> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Worklog> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Worklog> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Worklog> findAll() {
        return null;
    }

    @Override
    public Iterable<Worklog> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Worklog entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Worklog> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
