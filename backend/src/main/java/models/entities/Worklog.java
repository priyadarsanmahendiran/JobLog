package models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Entity
@Table(name = "worklog")
@Builder
@Data
public class Worklog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "worklog_id")
    public Long workLogId;

    @Column(name = "user_id")
    public Long userId;

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

    @ManyToOne
    public User user;
}
