package com.dao.JpaAndHibernate.services;

import com.dao.JpaAndHibernate.domain.PlayerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    PlayerEntity save(Long id, PlayerEntity playerEntity);
    List<PlayerEntity> getAllPlayers();

    Optional<PlayerEntity> getOnePlayer(Long id);

    boolean existId(Long id);

    PlayerEntity updatePlayerPartially(Long id, PlayerEntity playerEntity);

    void deleteById(Long id);

    Page<PlayerEntity> getAllPlayer(Pageable pageable);
}
