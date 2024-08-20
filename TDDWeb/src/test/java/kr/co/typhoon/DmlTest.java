package kr.co.typhoon;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.cj.xdevapi.PreparableStatement;

@RunWith(SpringJUnit4ClassRunner.class) // JUnit의 기본 테스트 러너 대신 @RunWith이용해 지정한 클래스(JUnit4ClassRunner)를 이용해 테스트 메소드를 수행하도록 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) 
public class DmlTest {
	@Autowired
	DataSource ds;
	
	@Test
	public void StringJdbcConnTest() throws SQLException {
		Connection conn = ds.getConnection();
		System.out.println("conn = " + conn);
		assertTrue(conn != null);
	}
		// 사용자 정보를 user 테이블에 저장하는 메서드
		public int insertUser(User user) throws Exception {
			Connection conn = ds.getConnection();
			
			String sql = "insert into user values (?, ?, ?, ?, ?, ?, now())";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
			pstmt.setString(6, user.getSns());
			
			int rowCnt = pstmt.executeUpdate();
			
			return rowCnt;
		}
		
		@Test
		public void insertUserTest() throws Exception {
			User user = new User("user1", "0819", "user1", "user1@gmail.com", new Date(), "fb", new Date());
			deleteAll(); 	//insert 전에 데이터를 전부 삭제 (문제 생기지 않게)		
			int rowCnt = insertUser(user);
			
			assertTrue(rowCnt == 1);
		}
		
		private void deleteAll() throws SQLException {
			Connection conn = ds.getConnection();
			String query = "delete from user";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate(); 	// insert, delete, update() 이 세가지만 executeUpdate 사용
		}
		
		public User selectUser(String id) throws Exception {	//pk가 id임
			Connection conn = ds.getConnection();
			
			String sql = "select * from user where id= ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();		// select 일때 사용 (실행하는 문)
			
			if(rs.next()) {
				User user = new User();
				user.setId(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setBirth(new Date(rs.getDate(5).getTime()));
				user.setSns(rs.getString(6));
				user.setReg_date(new Date(rs.getTimestamp(7).getTime()));
				return user;
			}
			return null;
		}
		
		@Test
		public void selectUserTest() throws Exception {
			deleteAll();
			User user = new User("user1", "0819", "user1", "user1@gmail.com", new Date(), "fb", new Date());
			int rowCnt = insertUser(user);
			User user2 = selectUser("user1");
			
			assertTrue(user.getId().equals("user1"));
		}
		
		public int deleteUser(String id) throws Exception {
			Connection conn = ds.getConnection();
			
			String sql = "delete from user where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}
		
		@Test
		public void deleteUserTest() throws Exception {
			deleteAll();
			int rowCnt = deleteUser("user1");
			
			assertTrue(rowCnt == 0); 	// 다 삭제되어 삭제될 것이 없는 상태임
			User user = new User("user2", "0819", "user2", "user2@gmail.com", new Date(), "fb", new Date());
			rowCnt = insertUser(user);
			assertTrue(rowCnt == 1);
			
			rowCnt = deleteUser(user.getId());
			assertTrue(rowCnt == 1);
		}
		
		public int updateUser(User user) throws Exception {
			Connection conn = ds.getConnection();
			
			String sql = "update user set pwd=?, name=?, email=?, birth=?, sns=?, reg_date=? where id= ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPwd());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
			pstmt.setString(5, user.getSns());
			pstmt.setDate(6, new java.sql.Date(user.getBirth().getTime()));
			pstmt.setString(7, user.getId());
			
			return pstmt.executeUpdate();
		}
		
		@Test
		public void updateUserTest() throws Exception {
			deleteAll();
			User user = new User("user1", "0820", "user1", "user1@gmail.com", new Date(), "fb", new Date());
			int rowCnt = insertUser(user);
			assertTrue(rowCnt == 1);
			
			user.setPwd("0821");
			user.setName("user11");
			user.setEmail("user11@gmail.com");
			rowCnt = updateUser(user);
			assertTrue(rowCnt == 1);
		}
		
	}

