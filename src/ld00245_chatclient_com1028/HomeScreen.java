package ld00245_chatclient_com1028;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
/**
 * 
 * @author PunLife
 *
 */
public class HomeScreen extends JFrame implements WriteToGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2735778510292493046L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextField ipText;
	private JTextField targetPort;
	private JTextField usernameText;
	private JTextField messageText;
	private JTextField receivePort;
	private JTextField errorField;
	private JTextArea chatText;
	private JButton lockButton;
	private JButton client1;
	private JButton client2;
	private JButton startButton;
	private JButton themeButton;
	private JButton exitButton;
	private JButton savechatButton;
	private JLabel clientLabel;
	private JLabel ipLabel;
	private JLabel listeningportLabel;
	private JLabel receivingportLabel;
	private JLabel usernameLabel;
	private MessageCapture capture;
	private MessageRelay relay;
	private int themeCounter = 0;
	private Date date;
	private DateFormat df1 = new SimpleDateFormat("dd_MM_yyyy HHmm");
	private DateFormat df = new SimpleDateFormat("HH:mm:ss");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreen frame = new HomeScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public HomeScreen() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(HomeScreen.class.getResource("/ld00245_chatclient_com1028/SanctuaryIcon.png")));
		setTitle("Sanctuary Chat Client");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ipText = new JTextField();
		ipText.setDisabledTextColor(Color.DARK_GRAY);
		ipText.setBackground(Color.WHITE);
		ipText.setBorder(new LineBorder(Color.BLACK, 1, true));
		ipText.setEditable(false);
		ipText.setText("localhost");
		ipText.setBounds(529, 119, 150, 20);
		contentPane.add(ipText);
		ipText.setColumns(10);

		targetPort = new JTextField();
		targetPort.setBackground(Color.WHITE);
		targetPort.setBorder(new LineBorder(Color.BLACK));
		targetPort.setEditable(false);
		targetPort.setText("9671");
		targetPort.setBounds(639, 172, 40, 20);
		contentPane.add(targetPort);
		targetPort.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 509, 303);
		contentPane.add(scrollPane);

		chatText = new JTextArea();
		chatText.setEditable(false);
		chatText.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		chatText.setForeground(Color.WHITE);
		chatText.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(chatText);

		messageText = new JTextField();
		messageText.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		messageText.setBounds(10, 360, 425, 20);
		contentPane.add(messageText);
		messageText.setColumns(10);

		JButton sendButton = new JButton("Send");
		sendButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		sendButton.setBackground(Color.WHITE);
		sendButton.addActionListener(new ActionListener() {
			/**
			 * Send method, if message text box is empty output an error, otherwise assign a new relay object and start the sending process.
			 */
			public void actionPerformed(ActionEvent e) {
				errorField.setText("");
				if (messageText.getText().trim().equals("")) {
					errorField.setText("ERROR:Input a message please.");
				} else {
					String temp = "Default";
					date = new Date();
					if (usernameText.getText().trim().equals("")) {
						temp = "AnonymousUser | " + df.format(date.getTime()) + ": " + messageText.getText();
					} else {
						temp = usernameText.getText() + " | " + df.format(date.getTime()) + ": "
								+ messageText.getText();
					}
					write(temp);
					relay = new MessageRelay(temp, ipText.getText(), Integer.parseInt(targetPort.getText()),HomeScreen.this);
					relay.start();
					messageText.setText("");
				}
			}
		});
		sendButton.setBounds(445, 359, 74, 23);
		contentPane.add(sendButton);

		receivePort = new JTextField();
		receivePort.setBackground(Color.WHITE);
		receivePort.setBorder(new LineBorder(Color.BLACK));
		receivePort.setEditable(false);
		receivePort.setText("9670");
		receivePort.setBounds(639, 150, 40, 20);
		contentPane.add(receivePort);
		receivePort.setColumns(10);

		lockButton = new JButton("Unlock options");
		lockButton.setBackground(Color.WHITE);
		lockButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		lockButton.setToolTipText("Press in order to unlock advanced options such as choosing address and ports.");
		lockButton.addActionListener(new ActionListener() {
			/**
			 * Allows  the user to lock or unlock the ip address and ports text fields, resets the field if settings are locked again
			 */
			public void actionPerformed(ActionEvent e) {
				if (lockButton.getText().equals("Unlock options")) {
					receivePort.setEditable(true);
					ipText.setEditable(true);
					targetPort.setEditable(true);
					lockButton.setText("Lock options");
				} else {
					receivePort.setText("9670");
					ipText.setText("localhost");
					targetPort.setText("9671");
					receivePort.setEditable(false);
					ipText.setEditable(false);
					targetPort.setEditable(false);
					lockButton.setText("Unlock options");
				}
				if (startButton.getText().equals("Start")) {
					startButton.setEnabled(true);
				}
			}
		});
		lockButton.setBounds(529, 70, 150, 23);
		contentPane.add(lockButton);

		client1 = new JButton("1");
		client1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		client1.setBackground(Color.WHITE);
		client1.addActionListener(new ActionListener() {
			/**
			 * Sets the default ports to Client1 configuration
			 */
			public void actionPerformed(ActionEvent e) {
				receivePort.setText("9670");
				targetPort.setText("9671");
				client1.setEnabled(false);
				client2.setEnabled(true);
				if (startButton.getText().equals("Start")) {
					startButton.setEnabled(true);
				} else {
					startButton.setEnabled(true);
					startButton.setText("Restart");
				}
			}
		});
		client1.setBounds(574, 12, 50, 25);
		contentPane.add(client1);

		clientLabel = new JLabel("Client #");
		clientLabel.setBounds(529, 16, 46, 14);
		contentPane.add(clientLabel);

		client2 = new JButton("2");
		client2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		client2.setBackground(Color.WHITE);
		client2.addActionListener(new ActionListener() {
			/**
			 * Sets the default ports to Client2 configuration
			 */
			public void actionPerformed(ActionEvent e) {
				receivePort.setText("9671");
				targetPort.setText("9670");
				client1.setEnabled(true);
				client2.setEnabled(false);
				if (startButton.getText().equals("Start")) {
					startButton.setEnabled(true);
				} else {
					startButton.setEnabled(true);
					startButton.setText("Restart");
				}
			}
		});
		client2.setBounds(629, 12, 50, 25);
		contentPane.add(client2);

		startButton = new JButton("Start");
		startButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		startButton.setBackground(Color.WHITE);
		startButton.setToolTipText("Press to Start the connection");
		startButton.setEnabled(false);
		startButton.addActionListener(new ActionListener() {
			/**
			 * Creates a new messagecapture object in order to listen for messages
			 */
			public void actionPerformed(ActionEvent e) {
				capture = new MessageCapture(Integer.parseInt(receivePort.getText()), HomeScreen.this);
				capture.start();
				startButton.setEnabled(false);
				startButton.setText("Restart");

			}
		});
		startButton.setBounds(529, 48, 150, 23);
		contentPane.add(startButton);

		usernameText = new JTextField();
		usernameText.setBorder(new LineBorder(Color.BLACK, 1, true));
		usernameText.setBounds(529, 218, 150, 20);
		contentPane.add(usernameText);
		usernameText.setColumns(10);

		usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(529, 203, 70, 14);
		contentPane.add(usernameLabel);

		themeButton = new JButton("Theme");
		themeButton.setBackground(Color.WHITE);
		themeButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		themeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * changes the colour of the interface to either the light or dark theme
				 */
				if (themeCounter == 0) {
					themeCounter++;
					contentPane.setBackground(Color.darkGray);
					messageText.setBackground(Color.black);
					messageText.setForeground(Color.white);
					usernameText.setBackground(Color.black);
					usernameText.setForeground(Color.white);
					themeButton.setBackground(Color.black);
					themeButton.setForeground(Color.white);
					savechatButton.setBackground(Color.black);
					savechatButton.setForeground(Color.white);
					sendButton.setBackground(Color.black);
					sendButton.setForeground(Color.white);
					exitButton.setBackground(Color.black);
					exitButton.setForeground(Color.white);
					lockButton.setBackground(Color.black);
					lockButton.setForeground(Color.white);
					client1.setBackground(Color.black);
					client1.setForeground(Color.white);
					client2.setBackground(Color.black);
					client2.setForeground(Color.white);
					startButton.setBackground(Color.black);
					startButton.setForeground(Color.white);
					ipText.setBackground(Color.black);
					ipText.setForeground(Color.white);
					receivePort.setBackground(Color.black);
					receivePort.setForeground(Color.white);
					targetPort.setBackground(Color.black);
					targetPort.setForeground(Color.white);
					clientLabel.setForeground(Color.white);
					ipLabel.setForeground(Color.white);
					listeningportLabel.setForeground(Color.white);
					receivingportLabel.setForeground(Color.white);
					usernameLabel.setForeground(Color.white);
					chatText.setBackground(Color.white);
					chatText.setForeground(Color.black);

				} else {
					themeCounter = 0;
					contentPane.setBackground(Color.white);
					messageText.setBackground(Color.white);
					messageText.setForeground(Color.black);
					usernameText.setBackground(Color.white);
					usernameText.setForeground(Color.black);
					themeButton.setBackground(Color.white);
					themeButton.setForeground(Color.black);
					savechatButton.setBackground(Color.white);
					savechatButton.setForeground(Color.black);
					sendButton.setBackground(Color.white);
					sendButton.setForeground(Color.black);
					exitButton.setBackground(Color.white);
					exitButton.setForeground(Color.black);
					lockButton.setBackground(Color.white);
					lockButton.setForeground(Color.black);
					client1.setBackground(Color.white);
					client1.setForeground(Color.black);
					client2.setBackground(Color.white);
					client2.setForeground(Color.black);
					startButton.setBackground(Color.white);
					startButton.setForeground(Color.black);
					ipText.setBackground(Color.white);
					ipText.setForeground(Color.black);
					receivePort.setBackground(Color.white);
					receivePort.setForeground(Color.black);
					targetPort.setBackground(Color.white);
					targetPort.setForeground(Color.black);
					clientLabel.setForeground(Color.black);
					ipLabel.setForeground(Color.black);
					listeningportLabel.setForeground(Color.black);
					receivingportLabel.setForeground(Color.black);
					usernameLabel.setForeground(Color.black);
					chatText.setBackground(Color.darkGray);
					chatText.setForeground(Color.white);

				}
			}
		});
		themeButton.setBounds(529, 249, 150, 23);
		contentPane.add(themeButton);

		savechatButton = new JButton("Save chat");
		savechatButton.setBackground(Color.WHITE);
		savechatButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		savechatButton.addActionListener(new ActionListener() {
			/**
			 * Saves a text file of the chat message area contents
			 */
			public void actionPerformed(ActionEvent e) {
				date = new Date();
				PrintWriter writer = null;
				try {
					writer = new PrintWriter("CHATLOG " + df1.format(date).toString() + ".txt", "UTF-8");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				writer.println(chatText.getText());
				writer.close();
			}
		});
		savechatButton.setBounds(529, 273, 150, 23);
		contentPane.add(savechatButton);

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			/**
			 * Exits the program
			 */
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBackground(Color.WHITE);
		exitButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		exitButton.setBounds(529, 359, 150, 23);
		contentPane.add(exitButton);

		ipLabel = new JLabel("IP Address:");
		ipLabel.setBounds(529, 104, 70, 14);
		contentPane.add(ipLabel);

		listeningportLabel = new JLabel("Listening Port:");
		listeningportLabel.setBounds(529, 153, 100, 14);
		contentPane.add(listeningportLabel);

		receivingportLabel = new JLabel("Receiving Port:");
		receivingportLabel.setBounds(529, 175, 100, 14);
		contentPane.add(receivingportLabel);

		errorField = new JTextField();
		errorField.setEditable(false);
		errorField.setText("Error Output");
		errorField.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		errorField.setBounds(10, 329, 669, 20);
		contentPane.add(errorField);
		errorField.setColumns(10);

		JLabel errorLabel = new JLabel("Error Console");
		errorLabel.setBounds(10, 315, 425, 14);
		contentPane.add(errorLabel);

	}

	/**
	 * Allows the program to write to GUI from other methods
	 */
	@Override
	public void write(String string) {
		chatText.append(string + System.lineSeparator());
	}
	/**
	 * Allows the program to write to GUI from other methods
	 */
	@Override
	public void writeError(String string) {
		errorField.setText(string);
	}
}
