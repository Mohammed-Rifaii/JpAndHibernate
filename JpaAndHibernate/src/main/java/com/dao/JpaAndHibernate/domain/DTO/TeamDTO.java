package com.dao.JpaAndHibernate.domain.DTO;

public class TeamDTO {
    private Long id;

    private String name;

    public TeamDTO() {
    }

    // All-arguments constructor
    public TeamDTO(Long id, String name) {
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
