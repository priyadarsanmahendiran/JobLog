package com.joblog.repositories.interfaces;

import com.joblog.models.entities.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IUserRepository extends CrudRepository<Users, UUID> {
}
