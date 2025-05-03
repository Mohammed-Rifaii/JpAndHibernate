package com.dao.JpaAndHibernate.services;

import com.dao.JpaAndHibernate.domain.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    TeamEntity save(TeamEntity teamEntity);

    List<TeamEntity> findAllTeams();

    Optional<TeamEntity> findTeamById(Long id);

    boolean existId(Long id);

    TeamEntity updateTeamPartially(Long id, TeamEntity teamEntity);

    void deleteById(Long id);

    Page<TeamEntity> findAllTeams(Pageable pageable);
}
