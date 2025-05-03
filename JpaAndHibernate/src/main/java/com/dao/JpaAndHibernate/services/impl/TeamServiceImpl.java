package com.dao.JpaAndHibernate.services.impl;

import com.dao.JpaAndHibernate.domain.PlayerEntity;
import com.dao.JpaAndHibernate.domain.TeamEntity;
import com.dao.JpaAndHibernate.repositories.PlayerRepository;
import com.dao.JpaAndHibernate.repositories.TeamRepository;
import com.dao.JpaAndHibernate.services.TeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;
    public TeamServiceImpl(TeamRepository teamRepository,PlayerRepository playerRepository){
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public TeamEntity save(TeamEntity team){
        return teamRepository.save(team);
    }

    public List<TeamEntity> findAllTeams(){
        Iterable<TeamEntity> allTeams= teamRepository.findAll();

        return StreamSupport.stream(allTeams
                .spliterator(),false)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<TeamEntity> findTeamById(Long id) {
        Optional<TeamEntity> teamEntity = teamRepository.findById(id);
        return teamEntity;
    }

    @Override
    public boolean existId(Long id) {
        return teamRepository.existsById(id);
    }

    @Override
    public TeamEntity updateTeamPartially(Long id, TeamEntity teamEntity) {
        teamEntity.setId(id);
        return teamRepository.findById(id).map(existingTeam -> {
                    Optional.ofNullable(teamEntity.getName()).ifPresent(existingTeam::setName);
                    //Optional.ofNullable(teamEntity.getName()).ifPresent(existingTeam::setName); IF OTHER FIELDS I JUST CHANGE THE NAME
                return teamRepository.save(existingTeam);
            }
        ).orElseThrow(()->new RuntimeException("Team Does not exist"));

    }

    @Override
    public void deleteById(Long id) {
        Optional<TeamEntity> teamEntity = teamRepository.findById(id);

        if(playerRepository.existsByTeamEntity(teamEntity.get())){
            throw new IllegalStateException("Cannot delete team: Players are still assigned to it.");
        }

        teamRepository.deleteById(id);
    }


    @Override
    public Page<TeamEntity> findAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);

    }
}
