package com.dao.JpaAndHibernate.controllers;

import com.dao.JpaAndHibernate.domain.DTO.PlayerDTO;
import com.dao.JpaAndHibernate.mapper.Mapper;
import com.dao.JpaAndHibernate.domain.PlayerEntity;
import com.dao.JpaAndHibernate.services.PlayerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PlayerController {


    private Mapper<PlayerEntity,PlayerDTO> playerMapperImpl;
    private PlayerService playerService;
    @Autowired
    public PlayerController(PlayerService playerService,Mapper<PlayerEntity,PlayerDTO> playerMapperImpl){
        this.playerService = playerService;
        this.playerMapperImpl = playerMapperImpl;
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<PlayerDTO> createNewPlayer(
            @PathVariable("id") Long id,
            @RequestBody final PlayerDTO playerDTO){
        boolean playerExist = playerService.existId(id);
        PlayerEntity playerEntity = playerMapperImpl.mapFrom(playerDTO);
        PlayerEntity savedPlayerEntity = playerService.save(id, playerEntity);

        if(playerExist){
            return new ResponseEntity<>(playerMapperImpl.mapTo(savedPlayerEntity),HttpStatus.OK);
        }
        else{
        return new ResponseEntity<>(playerMapperImpl.mapTo(savedPlayerEntity),HttpStatus.CREATED);
    }
    }

    @Value("${customer.variable.get}")
    private String helloString;

    @GetMapping("/HelloWorlds")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>(helloString,HttpStatus.OK);
    }

    @GetMapping("/players")
    public Page<PlayerDTO> returnAllPlayers(Pageable pageable){
        Page<PlayerEntity> players = playerService.getAllPlayer(pageable);
        return players.map(playerMapperImpl::mapTo);
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<PlayerDTO> getOneSpecificPlayer(@PathVariable("id") Long id){
        Optional<PlayerEntity> player = playerService.getOnePlayer(id);

        return player.map(playerFound -> {
            PlayerDTO playerDTO = playerMapperImpl.mapTo(playerFound);
            return new ResponseEntity<>(playerDTO,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PatchMapping("players/{id}")
    public ResponseEntity<PlayerDTO> updatePlayerPartially(
            @PathVariable("id") Long id,
            @RequestBody PlayerDTO playerDTO
    ){
        if(!playerService.existId(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            PlayerEntity playerEntity = playerMapperImpl.mapFrom(playerDTO);
            PlayerEntity savedPlayerEntity = playerService.updatePlayerPartially(id,playerEntity);

            return new ResponseEntity<>(playerMapperImpl.mapTo(savedPlayerEntity),HttpStatus.OK);
        }

    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity deletePlayerById(
            @PathVariable("id") Long id
    )
    {
        if(!playerService.existId(id))
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        playerService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

}
