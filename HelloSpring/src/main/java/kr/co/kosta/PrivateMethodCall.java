package kr.co.kosta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PrivateMethodCall {
	
	public static void main(String[] args) {
		HelloKosta helloKosta = new HelloKosta();
		//helloKosta.kosta();		// private 이라서 외부 호출 불가
		
		try {
			Class helloClass = Class.forName("kr.co.kosta.HelloKosta");	// HelloKosta 클래스의 클래스 정보를 담고 있는 객체를 얻어옴
			HelloKosta helloKosta2 = (HelloKosta)helloClass.newInstance();	// HelloKosta 객체가 가진 정보로 객체 생성
			
			Method kosta = helloClass.getDeclaredMethod("kosta");
				
			kosta.setAccessible(true);		// private인 kosta()를 접근가능하게 함
			
			kosta.invoke(helloKosta2); // helloKosta.kosta()와 동일함
			
			
			}catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
	}
}


