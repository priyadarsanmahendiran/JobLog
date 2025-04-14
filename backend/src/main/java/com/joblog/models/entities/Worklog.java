package com.joblog.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "work_logs")
@Builder
@Data
public class Worklog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "worklog_id")
    public UUID workLogId;

    @Column(name = "log_date")
    public Date logDate;

    @Column(name = "tasks_done")
    public String tasksDone;

    @Column(name = "tasks_planned")
    public String tasksPlanned;

    @Column(name = "blockers")
    public String blockers;

    @Column(name = "created_ts")
    @CreationTimestamp
    public Date createdAt;

    @Column(name = "last_updated_ts")
    @UpdateTimestamp
    public Date lastUpdatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public Users user;
}
