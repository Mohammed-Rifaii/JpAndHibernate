package com.dao.JpaAndHibernate.mapper.impl;

import com.dao.JpaAndHibernate.domain.DTO.TeamDTO;
import com.dao.JpaAndHibernate.mapper.Mapper;
import com.dao.JpaAndHibernate.domain.TeamEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TeamMapperImpl implements Mapper<TeamEntity, TeamDTO> {

    private ModelMapper modelMapper;

    public TeamMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public TeamEntity mapFrom(TeamDTO teamDTO){
        return modelMapper.map(teamDTO,TeamEntity.class);
    }

    public TeamDTO mapTo(TeamEntity teamEntity){
        return modelMapper.map(teamEntity,TeamDTO.class);
    }

    
}
