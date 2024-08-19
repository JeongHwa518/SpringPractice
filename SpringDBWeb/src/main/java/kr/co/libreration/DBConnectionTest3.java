package kr.co.libreration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DBConnectionTest3 {
	
	@Test
	public static void main(String[] args) throws SQLException {		//파일경로를 root-context.xml로 해서 bean 연결
		ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");		//** : 서브디렉토리에 있는 어떠한것도 상관없다 
		
		DataSource ds = ac.getBean(DataSource.class);
		
		// 데이터베이스 연결 얻기
		Connection conn = ds.getConnection();

		System.out.println("conn = " + conn);
	}
}
