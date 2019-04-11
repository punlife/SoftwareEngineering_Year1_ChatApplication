package ld00245_chatclient_com1028;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MessageRelay extends Thread {
	private String message;
	private String hostname;
	private int port;
	private Encrypter encrypter;
	private WriteToGUI gui;
	/**
	 * 
	 * @param message the message itself including timestamp and username
	 * @param hostname IP address of the client
	 * @param port 	port to connect to
	 * @param gui implemented version of the interface allowing to print to gui
	 */
	public MessageRelay(String message, String hostname, int port, WriteToGUI gui) {
		encrypter = new Encrypter();
		this.message = message;
		this.hostname = hostname;
		this.port = port;
		this.gui = gui;
	}
	/**
	 * overriden run method which runs a connection and sends a message through it
	 */
	@Override
	public void run() {
		try {
			Socket socket = new Socket(hostname, port);
			try {
				socket.getOutputStream().write(Encrypter.encrypt(message).getBytes());
			} catch (Exception e) {
				gui.writeError(e.getMessage());
				e.printStackTrace();
			}
			socket.close();
		} catch (UnknownHostException e) {
			gui.writeError(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			gui.writeError(e.getMessage());
			e.printStackTrace();
		}
	}
}
