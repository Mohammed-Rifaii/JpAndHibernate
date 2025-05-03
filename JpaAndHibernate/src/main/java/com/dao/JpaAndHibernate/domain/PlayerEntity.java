package com.dao.JpaAndHibernate.domain;

import jakarta.persistence.*;

@Entity
@Table(name ="player")
public class PlayerEntity {
    @Id

    private Long id;
    private String name;
    private String sport;
    private Integer age;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private TeamEntity teamEntity;

    //0 Param Construct
    public PlayerEntity(){

    }

    //All param Construct
    public PlayerEntity(Long id, String name, String sport, TeamEntity teamEntity, Integer age){
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.teamEntity = teamEntity;
        this.age =age;
    }

    //Getters
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getSport(){
        return sport;
    }

    public TeamEntity getTeam(){
        return teamEntity;
    }

    public Integer getAge(){ return age;}
    //Setters

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSport(String sport){
        this.sport = sport;
    }

    public void setAge(Integer age){
        this.age
                = age;
    }
    public void setTeam(TeamEntity teamEntity){
        this.teamEntity = teamEntity;
    }
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sport='" + sport + '\'' +
                '}' + ",age = " + age + ", team_id=" + teamEntity.toString();
    }


}
