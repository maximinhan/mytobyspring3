package springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.User;
/**
 * 
 * 일반적으로 테스트 케이스는 메이븐 프로젝트 빌드구조를 따라갈 경우
 * src/test/java 폴더 밑에 구성을 합니다.
 * 본 예제의 경우는 토비의 스프링을 공부하며 만들었기 때문에 따로 test폴더 아래 작성하지 않았습니다.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserDaoTest {
	/*
	 * @ContextConfiguration 어노테이션을 이용하여 applicationContext.xml을 읽어온다.(초기화)
	 * 스프링은 어플리케이션 컨텍스트를 초기화 할 때 자기자신도 빈으로 등록한다.
	 * 어플리케이션 컨텍스트를 통해 등록된 빈들이 이 클래스에 주입된다(아래 코드)
	 */
	
	@Autowired
	ApplicationContext context; //스프링 컨텍스트 (DI)
	
	@Autowired
	UserDao dao;  //UserDao (DI)
	
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() throws SQLException {
		this.dao = this.context.getBean("userDao", UserDao.class);
		
		this.user1 = new User("yuri", "유리", "rabbit");
		this.user2 = new User("roy", "로이", "cat");
		this.user3 = new User("riphi", "리피", "moonbird");
		//각 테스트에 사용될 user 객체 세팅(목 데이터)
			
		this.dao.deleteAll();
		//동등한 결과를 보장하기 위해 table 값을 모두 제거
	}
	
	@Test 
	public void andAndGet() throws SQLException {	
		assertThat(dao.getCount(), is(0));
		//빈 테이블 확인

		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		//등록된 로우가 2(콩)개
		
		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));
		//검증
		
		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
		//검증
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		//예외에 대한 테스트
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}

	
	@Test
	public void count() throws SQLException {
		//테이블 로우 수에 대한 테스트
		assertThat(dao.getCount(), is(0));
				
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
}
