package com.dao.JpaAndHibernate.controller;

import com.dao.JpaAndHibernate.DataUtil;
import com.dao.JpaAndHibernate.domain.DTO.TeamDTO;
import com.dao.JpaAndHibernate.domain.PlayerEntity;
import com.dao.JpaAndHibernate.domain.TeamEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import javax.xml.crypto.Data;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TeamControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public TeamControllerIntegrationTest(ObjectMapper objectMapper, MockMvc mockMvc) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }

    @Test
    public void TestIfTeamIsBeingCreatedAppropriately() throws Exception {
        TeamDTO team = DataUtil.createNewTeamA();
        team.setId(null);
        team.setName("Team");
        String teamJson = objectMapper.writeValueAsString(team);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void TestIfTeamIsBeingSavedRightObject() throws Exception {
        TeamDTO team = DataUtil.createNewTeamB();

        String teamJson = objectMapper.writeValueAsString(team);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(team.getName())
        );
    }

    @Test
    public void TestIfTeamIsReturningValue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teams")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    public void TestIfTeamIsreturningRightValues() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/teams")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber());
//    }

    @Test
    public void TestIfFindOneTeamIsBeingWitRightResponse() throws Exception {
        TeamDTO team = new TeamDTO(12L,"New Team 2");

        mockMvc.perform(MockMvcRequestBuilders.get("/teams/" + team.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.status().isOk());


    }

    @Test
    public void TestIfFindOneTeamIsBeingReturnedCorrectly() throws Exception {
        TeamDTO team = new TeamDTO(12L,null);

        mockMvc.perform(MockMvcRequestBuilders.get("/teams/" + team.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.id").isNumber());

    }

    @Test
    public void TestThatGetTeamsReturn404Status() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/teams/312321")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void TestThatUpdatedANonexistingTeamRaises200IfAvailable() throws Exception {
        TeamDTO teamDTO = DataUtil.createNewTeamA();
        teamDTO.setId(2L);

        String updatedTeamJson = objectMapper.writeValueAsString(teamDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/teams/"+teamDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedTeamJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }




    @Test
    public void TestThatUpdatedANonexistingTeamRaises404IfNotAvailable() throws Exception {
        TeamDTO teamDTO = DataUtil.createNewTeamA();
        teamDTO.setId(11231322L);

        String updatedTeamJson = objectMapper.writeValueAsString(teamDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/teams/"+teamDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTeamJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void TestThatUpdatedANonexistingTeamTestingSuccessfulPut() throws Exception {
        TeamDTO teamDTO = DataUtil.createNewTeamA();
        teamDTO.setName("Team For Testing Put");
        teamDTO.setId(2L);

        String updatedTeamJson = objectMapper.writeValueAsString(teamDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/teams/"+teamDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTeamJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(teamDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(teamDTO.getName()));
    }


    @Test
    public void TestThatPatchTeamsReturn200Status() throws Exception {
        TeamDTO team = new TeamDTO(12L,null);
        team.setName("New Name");

        String updateTeamJsonPatch = objectMapper.writeValueAsString(team);
        mockMvc.perform(MockMvcRequestBuilders.patch("/teams/"+ team.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateTeamJsonPatch)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void TestThatPatchTeamsReturnRightValues() throws Exception {
        TeamDTO team = new TeamDTO(12L,null);
        team.setName("New Name");

        String updateTeamJsonPatch = objectMapper.writeValueAsString(team);
        mockMvc.perform(MockMvcRequestBuilders.patch("/teams/"+ team.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateTeamJsonPatch)
        ).andExpect(MockMvcResultMatchers.jsonPath("name").value(team.getName()));
    }

    @Test
    public void TestThatTeamDeletionIsWorking() throws Exception {

        TeamDTO teamDTO = DataUtil.createNewTeamA();
        teamDTO.setId(55L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/teams/"+teamDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

//    @Test
//    public void TestThatTeamDeletionIsReturning204WhenNoIdAvailable() throws Exception {
//
//        TeamDTO teamDTO = DataUtil.createNewTeamA();
//        teamDTO.setId(8L);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/teams/"+teamDTO.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
}
