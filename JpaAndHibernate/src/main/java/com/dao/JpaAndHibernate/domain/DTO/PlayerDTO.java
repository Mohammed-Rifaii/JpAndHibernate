package com.dao.JpaAndHibernate.domain.DTO;



public class PlayerDTO {

    private Long id;
    private String name;
    private String sport;
    private Integer age;
    private TeamDTO teamDTO;

    //0 Param Construct
    public PlayerDTO(){

    }

    //All param Construct
    public PlayerDTO(Long id, String name, String sport, TeamDTO teamDTO, Integer age){
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.teamDTO = teamDTO;
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

    public TeamDTO getTeam(){
        return teamDTO;
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
    public void setTeam(TeamDTO teamDTO){
        this.teamDTO = teamDTO;
    }
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sport='" + sport + '\'' +
                '}' + ",age = " + age + ", team_id=" + teamDTO.toString();
    }
}
