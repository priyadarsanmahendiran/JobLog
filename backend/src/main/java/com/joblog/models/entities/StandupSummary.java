package com.joblog.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
public class StandupSummary {
  @Id @GeneratedValue private Long id;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "summary_date")
  private LocalDate summaryDate;

  @Column(name = "summary")
  private String summary;

  @CreationTimestamp
  @Column(name = "created_ts")
  private LocalDateTime createdTs;
}
