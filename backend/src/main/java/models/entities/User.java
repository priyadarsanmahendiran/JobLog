package models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long userId;

    @Column(name = "name")
    public String name;

    @Column(name = "email_id")
    public String emailId;

    @Column(name = "created_ts")
    @CreationTimestamp
    public Date createdAt;

    @OneToMany(mappedBy = "user_id")
    public List<Worklog> worklogs;

}
