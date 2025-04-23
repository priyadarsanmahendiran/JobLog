package com.joblog.repositories.interfaces;

import com.joblog.models.entities.Users;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<Users, UUID> {
  Optional<Users> findByEmailId(String emailId);
}
