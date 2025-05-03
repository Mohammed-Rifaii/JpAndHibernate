package com.dao.JpaAndHibernate.repositories;

import com.dao.JpaAndHibernate.domain.TeamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<TeamEntity, Long>,
        PagingAndSortingRepository<TeamEntity,Long> {
}
