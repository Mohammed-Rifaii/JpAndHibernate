//// ALl of here are integration since im using after unit testing and making sure in an actual scenario
//
//package com.dao.JpaAndHibernate.repositories;
//
//
//import com.dao.JpaAndHibernate.DataUtil;
//import com.dao.JpaAndHibernate.domain.PlayerEntity;
//import com.dao.JpaAndHibernate.domain.TeamEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ExtendWith({SpringExtension.class})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class PlayerEntityRepositorytegrationTest {
//
//    private PlayerRepository playerRepository;;
//    @Autowired
//    public PlayerEntityRepositorytegrationTest(PlayerRepository playerRepository){
//        this.playerRepository = playerRepository;
//    }
//
//    @Test
//    public void testGenerationSqlForPlayerDaoImpl(){
//        TeamEntity teamEntity = DataUtil.createNewTeamA();
//        teamEntity.setName("Villareal");
//        PlayerEntity playerEntity = DataUtil.createNewPlayerA(teamEntity);
//        playerRepository.save(playerEntity);
//
//
//        Optional<PlayerEntity> result = playerRepository.findById(playerEntity.getId());
//        assertThat(result).isPresent();
//        System.out.print(result.toString());
//    }
//
//
//
//    @Test
//    public void testGeneartionSqlForPlayerAllDaoImpl(){
//        TeamEntity teamEntityA = DataUtil.createNewTeamA();
//        TeamEntity teamEntityB = DataUtil.createNewTeamA();
//        TeamEntity teamEntityC = DataUtil.createNewTeamA();
//
//        teamEntityA.setName("Barca");
//        teamEntityB.setName("Madrid");
//        teamEntityC.setName("Villareal");
//        PlayerEntity playerEntityA = DataUtil.createNewPlayerA(teamEntityA);
//        PlayerEntity playerEntityB = DataUtil.createNewPlayerB(teamEntityB);
//        PlayerEntity playerEntityC = DataUtil.createNewPlayerC(teamEntityC);
//
//        playerRepository.save(playerEntityA);
//        playerRepository.save(playerEntityB);
//        playerRepository.save(playerEntityC);
//        Iterable<PlayerEntity> result = playerRepository.findAll();
//        System.out.print(result.toString());
//
//    }
//
//    @Test
//    public void IntegerationTestForPlayerUpdate(){
//        TeamEntity teamEntity = DataUtil.createNewTeamA();
//        teamEntity.setName("BOBO");
//        PlayerEntity playerEntity = DataUtil.createNewPlayerA(teamEntity);
//
//        playerRepository.save(playerEntity);
//        Optional<PlayerEntity> result = playerRepository.findById(playerEntity.getId());
//        System.out.println(result.toString());
//        playerEntity.setName("lolo");
//        playerEntity.setName("New Footballer");
//        playerRepository.save(playerEntity);
//        result = playerRepository.findById(playerEntity.getId());
//        System.out.println(result.toString());
//        assertThat(result).isPresent();
//    }
//
//    @Test
//    public void IntegrationTestForDeletePlayerSql(){
//        TeamEntity teamEntity = DataUtil.createNewTeamA();
//        teamEntity.setName("PLAP-PLOP2");
//        PlayerEntity playerEntity = DataUtil.createNewPlayerA(teamEntity);
//        playerRepository.save(playerEntity);
//        Iterable<PlayerEntity> result = playerRepository.findAll();
//        System.out.println(result.toString());
//        playerRepository.delete(playerEntity);
//        result = playerRepository.findAll();
//        System.out.println(result.toString());
//    }
//
//    @Test
//    public void IntegrationSqlToReturnPlayer(){
//        TeamEntity teamEntity = DataUtil.createNewTeamA();
//        teamEntity.setName("VADRID");
//        PlayerEntity playerEntityA = DataUtil.createNewPlayerA(teamEntity);
//        PlayerEntity playerEntityB = DataUtil.createNewPlayerB(teamEntity);
//        PlayerEntity playerEntityC = DataUtil.createNewPlayerC(teamEntity);
//
//        Iterable<PlayerEntity> result = playerRepository.ageLessThan(38);
//        System.out.print(result.toString());
//    }
//
//    @Test
//    public void IntegrationTocheckOldplayers(){
//        TeamEntity teamEntity = DataUtil.createNewTeamA();
//        teamEntity.setName("VADRID");
//        PlayerEntity playerEntityA = DataUtil.createNewPlayerA(teamEntity);
//        PlayerEntity playerEntityB = DataUtil.createNewPlayerB(teamEntity);
//        PlayerEntity playerEntityC = DataUtil.createNewPlayerC(teamEntity);
//
//        Iterable<PlayerEntity> result = playerRepository.getPlayersWithAgeGreaterThan(2);
//        System.out.print(result.toString());
//    }
//}
