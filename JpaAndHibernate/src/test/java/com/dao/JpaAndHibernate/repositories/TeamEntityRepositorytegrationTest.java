//package com.dao.JpaAndHibernate.repositories;
//
//
//import com.dao.JpaAndHibernate.DataUtil;
//import com.dao.JpaAndHibernate.domain.TeamEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class TeamEntityRepositorytegrationTest {
//
//    private TeamRepository teamRepository;
//
//
//    @Autowired
//    public TeamEntityRepositorytegrationTest(TeamRepository teamRepository){
//        this.teamRepository =teamRepository;
//    }
//
//
//    @Test
//    public void GenerateSqlTestFindOneForTeam(){
//        TeamEntity teamEntity = DataUtil.createNewTeamA();
//        teamRepository.save(teamEntity);
//
//        Optional<TeamEntity> result = teamRepository.findById(teamEntity.getId());
//        System.out.println(result.toString());
//        assertThat(result).isPresent();
//        System.out.print(result.toString());
////        assertThat(result.get()).isEqualTo(team);
//
//    }
//
//
//    @Test
//    @Transactional
//    public void GenerateTstFindTAllTeams(){
//        TeamEntity teamEntityA = DataUtil.createNewTeamA();
//        TeamEntity teamEntityB = DataUtil.createNewTeamA();
//        TeamEntity teamEntityC = DataUtil.createNewTeamA();
//        teamEntityA.setName("Barca");
//        teamEntityB.setName("Madrid");
//        teamEntityC.setName("Villareal");
//        teamRepository.save(teamEntityA);
//        teamRepository.save(teamEntityB);
//        teamRepository.save(teamEntityC);
//
//        Iterable<TeamEntity> result = teamRepository.findAll();
////        assertThat(result).containsExactly(teamA,teamB,teamC);
//        System.out.print(result.toString());
//
//    }
//
//
//    @Test
//    public void IntegrationTestForTeamUpdate(){
//        TeamEntity teamEntity = DataUtil.createNewTeamA();
//        teamEntity.setName("POPO");
//        teamRepository.save(teamEntity);
//        Optional<TeamEntity> result = teamRepository.findById(teamEntity.getId());
//        System.out.println(result.toString());
//
//        teamEntity.setName("Changed Team");
//        teamRepository.save(teamEntity);
//        result = teamRepository.findById(teamEntity.getId());
//        System.out.println(result.toString());
//    }
//
//    @Test
//    public void DeleteTestIfWorkingSql(){
//        TeamEntity teamEntity = DataUtil.createNewTeamA();
//        teamEntity.setName("PLAP-PLOP");
//        teamRepository.save(teamEntity);
//
//
//        Optional<TeamEntity> result = teamRepository.findById(teamEntity.getId());
//        System.out.println(result.toString());
//
//        teamRepository.deleteById(teamEntity.getId());
//        result = teamRepository.findById(teamEntity.getId());
//        System.out.print(result.toString());
//    }
//
//
//
//
//
//
//}
