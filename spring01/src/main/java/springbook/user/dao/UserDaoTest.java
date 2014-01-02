package springbook.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//어노테이션이 붙은 자바코드를 설정정보로 사용하겠다.
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		//xml(ApplicationContext)을 설정정보로 사용하겠다,
		//ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		//getBean 메소드로 오브젝트를 가져온다. 
		//userDao는 DaoFactory에서 @Bean 어노테이션이 붙은 메소드의 이름이다.
		//두번째 인자는 리턴타입이다. 써주지 않으면 캐스팅을 해야 한다.
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("spring");
		user.setName("books");
		user.setPassword("password123");

		dao.add(user);
			
		System.out.println(user.getId() + "입력");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
		System.out.println(user2.getId() + "출력");
	}
}
