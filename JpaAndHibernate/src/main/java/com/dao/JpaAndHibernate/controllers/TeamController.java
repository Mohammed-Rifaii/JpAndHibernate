package com.dao.JpaAndHibernate.controllers;

import com.dao.JpaAndHibernate.domain.DTO.TeamDTO;
import com.dao.JpaAndHibernate.domain.TeamEntity;
import com.dao.JpaAndHibernate.mapper.Mapper;
import com.dao.JpaAndHibernate.services.TeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TeamController {

    private TeamService teamService;
    private Mapper<TeamEntity,TeamDTO> teamMapperImp;

    public TeamController(TeamService teamService,  Mapper<TeamEntity,TeamDTO> teamMapperImp){
        this.teamMapperImp =teamMapperImp;
        this.teamService = teamService;
    }
    @PostMapping("/teams")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO){
    TeamEntity teamRetrievedEntity = teamMapperImp.mapFrom(teamDTO);
    TeamEntity savedTeamEntity = teamService.save(teamRetrievedEntity);

    return new ResponseEntity<>(teamMapperImp.mapTo(savedTeamEntity),HttpStatus.CREATED);
    }


    @GetMapping("/teams")
    public Page<TeamDTO> getAllTeams(Pageable pageable){
        Page<TeamEntity> teams= teamService.findAllTeams(pageable);

        return teams.map(teamMapperImp::mapTo);

    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<TeamDTO> getSepecficTeam(@PathVariable("id")Long id){
        Optional<TeamEntity> foundteam = teamService.findTeamById(id);
        return foundteam.map(foundTeams -> {
            TeamDTO teamDTO = teamMapperImp.mapTo(foundTeams);
            return new ResponseEntity<>(teamDTO,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<TeamDTO> fullUpdateTeam(@PathVariable("id") Long id,@RequestBody TeamDTO teamDTO){

        if(!teamService.existId(id))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            teamDTO.setId(id);
            TeamEntity teamEntity = teamMapperImp.mapFrom(teamDTO);
            TeamEntity savedTeamEntity = teamService.save(teamEntity);

            return new ResponseEntity<>(teamMapperImp.mapTo(savedTeamEntity),
                    HttpStatus.OK);
        }

    }

    @PatchMapping("/teams/{id}")
    public ResponseEntity<TeamDTO> updatePartialTeam(
            @PathVariable("id") Long id,
            @RequestBody TeamDTO teamDTO
    ){
        if(!teamService.existId(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            TeamEntity teamEntity = teamMapperImp.mapFrom(teamDTO);
            TeamEntity savedTeamEntity = teamService.updateTeamPartially(id,teamEntity);
            return new ResponseEntity<>(teamMapperImp.mapTo(savedTeamEntity),HttpStatus.OK);
        }
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity deleteTeamById(
            @PathVariable("id") Long id
    )
    {
        if (!teamService.existId(id))
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        teamService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
