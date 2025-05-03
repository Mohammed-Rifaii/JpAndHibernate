package com.dao.JpaAndHibernate.mapper.impl;

import com.dao.JpaAndHibernate.domain.DTO.PlayerDTO;
import com.dao.JpaAndHibernate.mapper.Mapper;
import com.dao.JpaAndHibernate.domain.PlayerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapperImpl implements Mapper<PlayerEntity, PlayerDTO> {

    public ModelMapper modelMapper;

    public PlayerMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public PlayerEntity mapFrom(PlayerDTO playerDTO){
        return modelMapper.map(playerDTO, PlayerEntity.class);
    }

    @Override
    public PlayerDTO mapTo(PlayerEntity playerEntity){
        return modelMapper.map(playerEntity, PlayerDTO.class);
    }
}
