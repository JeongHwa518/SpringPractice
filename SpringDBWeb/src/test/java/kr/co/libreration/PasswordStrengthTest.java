package kr.co.libreration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PasswordStrengthTest {
	
	@Test
	void needsAllCriteria() {
		PasswordStrengthMeter meter = new PasswordStrengthMeter();
		PasswordStrength result = meter.meter("ab12!@ABC");
		assertEquals(PasswordStrength.STRONG, result);
	}
	
	@Test
	void needsSomeCriteria() {
		PasswordStrengthMeter meter = new PasswordStrengthMeter();
		PasswordStrength result = meter.meter("ab12!AB");
		assertEquals(PasswordStrength.NORMAL, result);
	}
}
