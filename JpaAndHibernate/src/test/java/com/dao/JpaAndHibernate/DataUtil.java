package com.dao.JpaAndHibernate;

import com.dao.JpaAndHibernate.domain.DTO.PlayerDTO;
import com.dao.JpaAndHibernate.domain.DTO.TeamDTO;
import com.dao.JpaAndHibernate.domain.PlayerEntity;
import com.dao.JpaAndHibernate.domain.TeamEntity;

public class DataUtil {
    private DataUtil(){};

    public static TeamDTO createNewTeamA() {
        return new TeamDTO();
    }

    public static TeamDTO createNewTeamB() {
        return new TeamDTO(2L, "Alaves");
    }

    public static TeamDTO createNewTeamC() {
        return new TeamDTO(3L, "Athletic Club");
    }

    public static PlayerDTO createNewPlayerA(TeamDTO teamDto) {
        return new PlayerDTO(2L, "Luis Suarez", "Football", teamDto,34);
    }

    public static PlayerDTO createNewPlayerB(TeamDTO teamDto) {
        return new PlayerDTO(3L, "Neymar Jr.", "Football", teamDto,35);
    }

    public static PlayerDTO createNewPlayerC(TeamDTO teamDto){
        return new PlayerDTO(6L,"Gareth Bale","Football", teamDto,36);
    }
}
