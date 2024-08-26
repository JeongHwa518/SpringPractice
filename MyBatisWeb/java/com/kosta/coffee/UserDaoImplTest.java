package com.kosta.coffee;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class) // JUnit의 기본 테스트 러너 대신 @RunWith이용해 지정한 클래스(JUnit4ClassRunner)를 이용해 테스트 메소드를 수행하도록 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // 문자열을 이용해 필요한 객체들을 스프링 내에 객체로 등록
public class UserDaoImplTest {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	DataSource ds;
	
	@Test
	public void insertUserTest() throws Exception {
		User user = new User("user1", "0819", "user1", "user1@gmail.com", new Date(), "fb", new Date());
		userDao.deleteAll(); 	//insert 전에 데이터를 전부 삭제 (문제 생기지 않게)		
		int rowCnt = userDao.insertUser(user);
		
		assertTrue(rowCnt == 1);
	}

	private void deleteAll() throws SQLException {
		Connection conn = ds.getConnection();
		
		String query = "delete from user";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.executeUpdate(); 	// insert, delete, update() 이 세가지만 executeUpdate 사용
	}
	
}
