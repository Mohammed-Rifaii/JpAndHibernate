package com.dao.JpaAndHibernate.repositories;

import com.dao.JpaAndHibernate.domain.PlayerEntity;
import com.dao.JpaAndHibernate.domain.TeamEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity,Long>, PagingAndSortingRepository<PlayerEntity, Long> {

    Iterable<PlayerEntity> ageLessThan(Integer age);

    boolean existsByTeamEntity(TeamEntity team);

    @Query("SELECT a FROM PlayerEntity a where a.age > ?1 ")
    Iterable<PlayerEntity> getPlayersWithAgeGreaterThan(Integer age);
}
