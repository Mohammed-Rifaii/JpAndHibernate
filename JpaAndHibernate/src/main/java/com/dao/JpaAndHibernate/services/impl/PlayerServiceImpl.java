package com.dao.JpaAndHibernate.services.impl;

import com.dao.JpaAndHibernate.domain.DTO.PlayerDTO;
import com.dao.JpaAndHibernate.domain.PlayerEntity;
import com.dao.JpaAndHibernate.repositories.PlayerRepository;
import com.dao.JpaAndHibernate.services.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;
    private Long id;
    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public PlayerEntity save(Long id, PlayerEntity playerEntity){
        playerEntity.setId(id);
        return playerRepository.save(playerEntity);
    }

    public List<PlayerEntity> getAllPlayers(){
      return  StreamSupport.stream(playerRepository
              .findAll()
              .spliterator(),false)
              .collect(Collectors.toList());
    }

    public Optional<PlayerEntity> getOnePlayer(Long id){
        return playerRepository.findById(id);
    }

    @Override
    public boolean existId(Long id) {
        return playerRepository.existsById(id);
    }

    @Override
    public PlayerEntity updatePlayerPartially(Long id, PlayerEntity playerEntity) {
        playerEntity.setId(id);//setting is the first step
        // I need to map the one in the database and replace it with values of one in playerEntity thats why i map from the retrieved data
        return playerRepository.findById(id).map(existingPlayer -> {
            Optional.ofNullable(playerEntity.getAge()).ifPresent(existingPlayer::setAge); // the existingPlayer is Optional thats why we're usong Optional.ofNullabe to check if palyerEntity sent is nulalble
            Optional.ofNullable(playerEntity.getName()).ifPresent(existingPlayer::setName);
            Optional.ofNullable(playerEntity.getSport()).ifPresent(existingPlayer::setSport);
            Optional.ofNullable(playerEntity.getTeam()).ifPresent(existingPlayer::setTeam);

            return playerRepository.save(existingPlayer);//always remember to save in db since its patch! and make it in return
        }).orElseThrow(() -> new RuntimeException("Not A valid Id"));
    }

    @Override
    public void deleteById(Long id) {
       playerRepository.deleteById(id);
    }

    @Override
    public Page<PlayerEntity> getAllPlayer(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }
}
