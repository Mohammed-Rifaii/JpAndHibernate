package com.dao.JpaAndHibernate.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "team_seq_id")
    @SequenceGenerator(name = "team_seq_id", sequenceName = "team_seq", allocationSize = 1)
    private Long id;

    private String name;

    public TeamEntity() {
    }

    // All-arguments constructor
    public TeamEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team[id=" + id + ", name=" + name + "]"; // Stable format
    }
}
