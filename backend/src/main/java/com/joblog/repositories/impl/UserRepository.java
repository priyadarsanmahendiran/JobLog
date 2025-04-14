package com.joblog.repositories.impl;

import com.joblog.models.entities.Users;
import org.springframework.stereotype.Repository;
import com.joblog.repositories.interfaces.IUserRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository implements IUserRepository {

    @Override
    public <S extends Users> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Users> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Users> findById(UUID aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID aLong) {
        return false;
    }

    @Override
    public Iterable<Users> findAll() {
        return null;
    }

    @Override
    public Iterable<Users> findAllById(Iterable<UUID> longs) {
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
    public void delete(Users entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Users> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
