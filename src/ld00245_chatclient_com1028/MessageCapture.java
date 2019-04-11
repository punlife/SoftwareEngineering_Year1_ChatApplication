package ld00245_chatclient_com1028;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 
 * @author PunLife
 *
 */
public class MessageCapture extends Thread {
	private ServerSocket server;
	private int port = 9670;
	private WriteToGUI gui;
	private Encrypter encrypter;
	
	/**
	 * 
	 * @param port the listening port from which to capture messages
	 * @param gui implemented interface allowing the object to print message to gui
	 */
	public MessageCapture(int port, WriteToGUI gui) {
		encrypter = new Encrypter();
		this.port = port;
		this.gui = gui;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			gui.writeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public MessageCapture() {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Overriden run method which triggers the connection and capture messages from a separate thread
	 */
	@Override
	public void run() {
		Socket client;
		try {
			while ((client = server.accept()) != null) {
				InputStream is = client.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = br.readLine();

				if (line != null) {
					try {
						gui.write(Encrypter.decrypt(line));
					} catch (Exception e) {
						gui.writeError(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			gui.writeError(e.getMessage());
			e.printStackTrace();
		}
	}
}
