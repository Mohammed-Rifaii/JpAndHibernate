package com.dao.JpaAndHibernate;

//import com.dao.JpaAndHibernate.DAO.impl.PlayerDAOImpl;
//import com.dao.JpaAndHibernate.DAO.impl.TeamDAOImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JpaAndHibernateApplication {
//`	private JdbcTemplate jdbcTemplate;
//	private PlayerDAOImpl playerDaoImpl;
//	private TeamDAOImpl teamDaoImpl;
//	public JpaAndHibernateApplication(JdbcTemplate jdbcTemplate, PlayerDAOImpl playerDaoImpl, TeamDAOImpl teamDaoImpl){
//		this.jdbcTemplate = jdbcTemplate;
//		this.playerDaoImpl = playerDaoImpl;
//		this.teamDaoImpl = teamDaoImpl;
//	}
	public static void main(String[] args) {
		SpringApplication.run(JpaAndHibernateApplication.class, args);
	}
// Just adding note
/*		@PostConstruct
	public void init() {
	List<Map<String,Object>> result1 = jdbcTemplate.queryForList("select * from player");
		System.out.println(result1.toString());

		List<Map<String,Object>> result2 = jdbcTemplate.queryForList("select * from team");
		System.out.println(result2.toString());

		Team team = new Team(3L,"Atletico Madrid");
		teamDaoImpl.create(team);
		List<Map<String,Object>> result3 = jdbcTemplate.queryForList("select * from team");
		System.out.println(result3.toString());

		Optional<Team> result4 = teamDaoImpl.findOne(3L);
		System.out.println(result4);

		Player player = new Player(3L,"Roger Federrer","Tennis",3L);
		playerDaoImpl.create(player);
		List<Map<String,Object>> result = jdbcTemplate.queryForList("select * from player");

		System.out.println(result.toString());

		Optional<Player> findOneTest= playerDaoImpl.findOne(3L);
		System.out.println(findOneTest.toString());

 	}
*/
}
