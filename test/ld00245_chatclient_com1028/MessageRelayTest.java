package ld00245_chatclient_com1028;

import static org.junit.Assert.*;

import org.junit.Test;

public class MessageRelayTest {

	@Test
	public void testMessageRelay() {
		WriteToGUI gui = null;
		String msg = "hi";
		String address = "localhost";
		int port = 9670;
		MessageRelay message = new MessageRelay(msg, address, port, gui);
		assertEquals(message, message);
	}

}
