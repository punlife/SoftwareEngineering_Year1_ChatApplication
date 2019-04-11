package ld00245_chatclient_com1028;

import static org.junit.Assert.*;

import org.junit.Test;

public class MessageCaptureTest {

	@Test
	public void testMessageCaptureIntWriteToGUI() {
		int port = 9671;
		WriteToGUI gui = null;
		MessageCapture capture = new MessageCapture(port, gui);
		assertEquals(capture, capture);
	}

	@Test
	public void testMessageCapture() {
		MessageCapture capture = new MessageCapture();
		assertEquals(capture, capture);
	}

}
