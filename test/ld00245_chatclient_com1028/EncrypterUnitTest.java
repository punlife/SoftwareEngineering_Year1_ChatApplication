package ld00245_chatclient_com1028;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class EncrypterUnitTest {

	@Test
	public void testEncrypt() throws Exception {
		Encrypter encrypter = new Encrypter();
		assertThat(encrypter.encrypt("hi"), not(equalTo("hi")));
		assertThat(encrypter.encrypt("hello there"), not(equalTo("hello there")));
		assertThat(encrypter.encrypt("1234"), not(equalTo("1234")));
		assertThat(encrypter.encrypt("$£%$%^%$"), not(equalTo("$£%$%^%$")));
	}

	@Test
	public void testDecrypt() throws Exception {
		Encrypter encrypter = new Encrypter();
		assertEquals(encrypter.decrypt(encrypter.encrypt("hi")),"hi");
		assertEquals(encrypter.decrypt(encrypter.encrypt("test")),"test");
		assertEquals(encrypter.decrypt(encrypter.encrypt("1234")),"1234");
		assertEquals(encrypter.decrypt(encrypter.encrypt("$£%$%^%$")),"$£%$%^%$");
	}

}
