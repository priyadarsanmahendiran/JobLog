package com.joblog.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

  @Id @GeneratedValue private UUID id;

  @Column(name = "name")
  public String name;

  @Column(name = "email_id")
  public String emailId;

  @Column(name = "created_ts")
  @CreationTimestamp
  public Date createdAt;
}
