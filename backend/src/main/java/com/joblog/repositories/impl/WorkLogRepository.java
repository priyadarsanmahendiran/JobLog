package com.joblog.repositories.impl;

import com.joblog.models.entities.Worklog;
import org.springframework.stereotype.Repository;
import com.joblog.repositories.interfaces.IWorkLogRepository;

import java.util.Optional;
import java.util.UUID;


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
    public Optional<Worklog> findById(UUID aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID aLong) {
        return false;
    }

    @Override
    public Iterable<Worklog> findAll() {
        return null;
    }

    @Override
    public Iterable<Worklog> findAllById(Iterable<UUID> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID aLong) {

    }

    @Override
    public void delete(Worklog entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Worklog> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
