package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 해당 클래스의 경우 자바코드, 즉 어노테이션을 이용한 의존관계 주입시에만 사용되는 클래스 이다.
 * xml을 이용한 의존관계 주입 및 xml을 이용한 DataSource 인터페이스 이용시에는 해당 클래스는 사실 필요가 없다. *
 */
//어플리케이션 컨텍스트 또는 빈팩토리가 사용할 설정정보라는 어노테이션
@Configuration
public class DaoFactory {
	//오브젝트 생성을 담당하는 IoC용 메소드라는 표시
	@Bean
	public UserDao userDao() {
		UserDao dao = new UserDao();
		dao.setConnectionMaker(connectionMaker());
		return dao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		ConnectionMaker connectionMaker = new DConnectionMaker();
		return connectionMaker;
	}
}
