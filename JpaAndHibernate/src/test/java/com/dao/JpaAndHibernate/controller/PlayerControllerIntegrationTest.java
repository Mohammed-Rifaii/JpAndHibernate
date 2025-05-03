package com.dao.JpaAndHibernate.controller;

import com.dao.JpaAndHibernate.DataUtil;
import com.dao.JpaAndHibernate.domain.DTO.PlayerDTO;
import com.dao.JpaAndHibernate.domain.DTO.TeamDTO;
import com.dao.JpaAndHibernate.domain.PlayerEntity;
import com.dao.JpaAndHibernate.services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.dao.JpaAndHibernate.mapper.Mapper;

import javax.xml.crypto.Data;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PlayerControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private PlayerService playerService;
    private Mapper<PlayerEntity,PlayerDTO> playerMapper;
    @Autowired
    public PlayerControllerIntegrationTest(MockMvc mockmvc,PlayerService playerService,Mapper<PlayerEntity,PlayerDTO> playerMapper){
        this.mockMvc=mockmvc;
        this.objectMapper = new ObjectMapper();
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @Test
    public void testThatCreatePlayerSuccessfullyReturns201Created() throws Exception {
        TeamDTO teamDTO = DataUtil.createNewTeamA();
        teamDTO.setName("team");
        PlayerDTO playerA = DataUtil.createNewPlayerA(teamDTO);
        playerA.setId(131234L);

        String playerJson = objectMapper.writeValueAsString(playerA);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/players/" + playerA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreatePlayerSuccessfullyReturnsSavedPlayer() throws Exception {
        TeamDTO teamDTO = DataUtil.createNewTeamA();
        teamDTO.setName("llal");
        PlayerDTO playerA = DataUtil.createNewPlayerA(teamDTO);


        String playerJson = objectMapper.writeValueAsString(playerA);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/players/"+ playerA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(playerA.getId())
        ).andExpect( MockMvcResultMatchers.jsonPath("$.name").value("Luis Suarez")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.age").value(34)

        );

    }

    @Test
    public void testThaGetAllPlayersIsWorkingFine() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/players")
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk());

    }

//    @Test
//    public void testThaGetAllPlayersIsReturningRightValues() throws Exception {
//        TeamDTO teamDTO = DataUtil.createNewTeamA();
//        teamDTO.setName("name new");
//        PlayerDTO playerDTO = DataUtil.createNewPlayerA(null);
//        PlayerEntity playerEntity = playerMapper.mapFrom(playerDTO);
//        playerService.save(1L,playerEntity);
//        mockMvc.perform(MockMvcRequestBuilders.get("/players")
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
//        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(25));
//    }

    @Test
    public void TestThatGetPlayersReturnRightStatus() throws Exception {
        TeamDTO teamDTO = DataUtil.createNewTeamA();
        teamDTO.setName("name new");
        PlayerDTO playerDTO = DataUtil.createNewPlayerA(null);
        PlayerEntity playerEntity = playerMapper.mapFrom(playerDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/players/"+ playerEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void TestThatGetPlayersReturnValues() throws Exception {
        TeamDTO teamDTO = DataUtil.createNewTeamA();
        teamDTO.setName("name new");
        PlayerDTO playerDTO = DataUtil.createNewPlayerA(null);
        PlayerEntity playerEntity = playerMapper.mapFrom(playerDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/players/"+ playerEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber());
    }

    @Test
    public void TestThatGetPlayersReturn404Status() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/players/1231231")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    public void TestThatPutForExistentPlayersReturn200StatusForExistingPlayer() throws Exception {
        TeamDTO teamDTO = DataUtil.createNewTeamB();
        PlayerDTO playerDTO = DataUtil.createNewPlayerA(teamDTO);
        playerDTO.setId(71L);
        String jsonUpdatedPlayer = objectMapper.writeValueAsString(playerDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/players/"+playerDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUpdatedPlayer)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void TestThatPutForExistentisBeingUpdatedCorrectly() throws Exception {
        TeamDTO teamDTO = DataUtil.createNewTeamB();
        PlayerDTO playerDTO = DataUtil.createNewPlayerA(teamDTO);
        playerDTO.setId(71L);
        playerDTO.setName("name Test");
        //i can save the player into database but i know that theres a team and player with these details so thats why im using directly
        //in a proper test i should add the player to db using save then complete the testing
        String jsonUpdatedPlayer = objectMapper.writeValueAsString(playerDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/players/"+playerDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUpdatedPlayer)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(playerDTO.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name Test")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.sport").value(playerDTO.getSport()));
    }

    @Test
    public void TestThatPatchForExistentisBeingUpdatedCorrectly() throws Exception {
        TeamDTO teamDTO = DataUtil.createNewTeamB();
        PlayerDTO playerDTO = DataUtil.createNewPlayerA(teamDTO);
        playerDTO.setId(71L);
        playerDTO.setName("name Test");

        String jsonUpdatedPlayer = objectMapper.writeValueAsString(playerDTO);
        mockMvc.perform(MockMvcRequestBuilders.patch("/players/"+playerDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUpdatedPlayer)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(playerDTO.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name Test")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.sport").value(playerDTO.getSport()));
    }

    @Test
    public void TestThatPatchReturns200IfExists() throws Exception{
        TeamDTO teamDTO = DataUtil.createNewTeamB();
        PlayerDTO playerDTO = DataUtil.createNewPlayerA(teamDTO);
        playerDTO.setId(71L);
        playerDTO.setName("name Test");

        String patchedJson = objectMapper.writeValueAsString(playerDTO);
        mockMvc.perform(MockMvcRequestBuilders.patch("/players/"+playerDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(patchedJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(playerDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sport").value(playerDTO.getSport()));
    }

//  @Test
//    public void TestThatPlayerDeletionIsWorking() throws Exception {
//        TeamDTO teamDTO = DataUtil.createNewTeamB();
//        PlayerDTO playerDTO = DataUtil.createNewPlayerA(teamDTO);
//        playerDTO.setId(69L);
//        playerDTO.setName("name Test");
//        PlayerEntity playerEntity = playerMapper.mapFrom(playerDTO);
//        playerService.save(playerDTO.getId(),playerEntity);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/players/"+ playerEntity.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//    }

    @Test
    public void TestThatPlayerDeletionIsReturning204WhenNoIdAvailable() throws Exception {
        TeamDTO teamDTO = DataUtil.createNewTeamB();
        PlayerDTO playerDTO = DataUtil.createNewPlayerA(teamDTO);
        playerDTO.setId(14412122L);
        playerDTO.setName("name Test");


        mockMvc.perform(MockMvcRequestBuilders.delete("/players/"+ playerDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
