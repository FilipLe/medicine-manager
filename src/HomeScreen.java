import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSplitPane;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Icon;


/**
 * 
 * @author nguyenle
 *
 * Home Screen of Application
 */

/*
 * ISSUES TO FIX:
 * When IRL 10 AM --> Program shows 10:00
 * When IRL 10 PM --> Program also shows 10:00
 * 
 * Program does not distinguish difference between AM and PM
 */
public class HomeScreen {

	private JFrame frame;
	JLabel lbltime;
	JLabel lblDate;
	JLabel lblAMorPM;
	String dayOfWeek;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreen window = new HomeScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Function to display date and time
	 * 
	 * 
	 * Link to youtube tutorial:
	 * https://www.youtube.com/watch?v=tpQAslXjNKU
	 */
	public void time() {		
		//Making a thread to allow the time to run real time
		Thread time = new Thread()
		{
			public void run() {
				try {
					for(;;) {
						Calendar calendar = new GregorianCalendar();
						int hour = calendar.get(Calendar.HOUR_OF_DAY);
						int minute = calendar.get(Calendar.MINUTE);
						int seconds = calendar.get(Calendar.SECOND);
						
						lbltime.setText(hour + ":" + minute + ":" + seconds);
						
						//Bunch of If statements to determine whether to put "AM" or "PM"
						/*
						 * PM
						 */
						if(hour > 12 && seconds < 10 && minute < 10) {
							lbltime.setText(hour + ":0" + minute + ":0" + seconds);
							lblAMorPM.setText("PM");
							
						}
						else if(hour > 12 && seconds < 10 && minute > 10) {
							
							lbltime.setText(hour + ":" + minute + ":0" + seconds);
							lblAMorPM.setText("PM");
						}
						//----
						else if(hour > 12 && seconds > 10 && minute < 10) {
							
							lbltime.setText(hour + ":0" + minute + ":" + seconds);
							lblAMorPM.setText("PM");
						}
						else if(hour > 12 && seconds > 10 && minute > 10) {
							
							lbltime.setText(hour + ":" + minute + ":" + seconds);
							lblAMorPM.setText("PM");
						}
						/*
						 * AM
						 */
						//----
						else if(hour <= 12 && seconds < 10 && minute < 10){
							lbltime.setText(hour + ":0" + minute + ":0" + seconds);
							lblAMorPM.setText("AM");
						}
						else if(hour <= 12 && seconds < 10 && minute > 10){
							lbltime.setText(hour + ":" + minute + ":0" + seconds);
							lblAMorPM.setText("AM");
						}
						//----
						else if (hour <= 12 && seconds > 10 && minute < 10){
							lbltime.setText(hour + ":0" + minute + ":" + seconds);
							lblAMorPM.setText("AM");
						}
						else if (hour <= 12 && seconds > 10 && minute > 10){
							lbltime.setText(hour + ":" + minute + ":" + seconds);
							lblAMorPM.setText("AM");
						}
						
						//Update every 1000 miliseconds (1 second)
						sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		time.start();
	}
	
	public void date() {
		//Making a thread to allow the time to run real time
		Thread date = new Thread() 
		{
			public void run() {
				try {
					for(;;) {
						Calendar calendar = new GregorianCalendar();
						
						int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
						if(dayWeek == 2) {
							dayOfWeek = "Monday";
						}
						else if(dayWeek == 3) {
							dayOfWeek = "Tuesday";
						}
						else if(dayWeek == 4) {
							dayOfWeek = "Wednesday";
						}
						else if(dayWeek == 5) {
							dayOfWeek = "Thursday";
						}
						else if(dayWeek == 6) {
							dayOfWeek = "Friday";
						}
						else if(dayWeek == 7) {
							dayOfWeek = "Saturday";
						}
						//Wehn tested, in Gregorian Calendar, Sunday is 1st day of the week
						else if(dayWeek == 1) {
							dayOfWeek = "Sunday";
						}
						
						int day = calendar.get(Calendar.DAY_OF_MONTH);
						int month = calendar.get(Calendar.MONTH)+ 1; // for some reason it is 1 month off
						int year = calendar.get(Calendar.YEAR);
						lblDate.setText(dayOfWeek + ", " + day + "-" + month + "-" + year);
						sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};		
		date.start();
	}
	
	

	/**
	 * Create the application.
	 */
	public HomeScreen() {
		initialize();
		time();
		date();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Home Medication Tracker - Home Screen");
		//Frame light blue background
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		 * Take user to Admin Login Screen
		 */
		JButton adminLoginButton = new JButton("Admin");
		adminLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close window
				frame.dispose();
				//take to admin login window
				AdminLoginScreen.main(null);
			}
		});
		adminLoginButton.setBounds(110, 144, 229, 45);
		frame.getContentPane().add(adminLoginButton);
				
		/*
		 * Take user to Family Member Login
		 */
		JButton teacherLoginButton = new JButton("User");
		teacherLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close window
				frame.dispose();
				//Launch Family Member Login Window
				UserLoginScreen.main(null);
			}
		});
		teacherLoginButton.setBounds(110, 201, 229, 45);
		frame.getContentPane().add(teacherLoginButton);
		
		//Button to quit --> Back to StartUp Screen
		JButton btnLogOut = new JButton("Quit");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close window
				frame.dispose();
				//Open Startup screen
				UIMain.main(null);
			}
		});
		btnLogOut.setBounds(336, 237, 108, 29);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblPleaseSelectUser = new JLabel("Please select user type:");
		lblPleaseSelectUser.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblPleaseSelectUser.setBounds(18, 104, 241, 28);
		frame.getContentPane().add(lblPleaseSelectUser);
		
		//Area which displays current time
		lbltime = new JLabel("03:12:47");
		lbltime.setFont(new Font("Lucida Grande", Font.BOLD, 41));
		lbltime.setBounds(125, -1, 204, 68);
		frame.getContentPane().add(lbltime);
		
		//Area which displays current date
		lblDate = new JLabel("Wednesday, 10-07-2021");
		lblDate.setBounds(149, 63, 173, 17);
		frame.getContentPane().add(lblDate);
		
		//Label for AM or PM
		lblAMorPM = new JLabel("PM");
		lblAMorPM.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblAMorPM.setBounds(310, 33, 61, 16);
		frame.getContentPane().add(lblAMorPM);
		
		//Admin Icon
		JLabel lblAdminIcon = new JLabel(new ImageIcon("/Users/nguyenle/workspace-2021-swing-applications/Medicine_Manager_REAL/src/UncoloredAdminIcon.png"));
		lblAdminIcon.setBounds(68, 141, 42, 55);
		frame.getContentPane().add(lblAdminIcon);
		
		//User Icon
		JLabel lblUserIcon = new JLabel(new ImageIcon("/Users/nguyenle/workspace-2021-swing-applications/Medicine_Manager_REAL/src/uncoloredicon.png"));
		lblUserIcon.setBounds(68, 196, 45, 50);
		frame.getContentPane().add(lblUserIcon);
		
		//Logo Icon
		JLabel lblLogo = new JLabel(new ImageIcon("/Users/nguyenle/workspace-2021-swing-applications/Medicine_Manager_REAL/src/NguyenLogo.png"));
		lblLogo.setBounds(3, 0, 100, 93);
		frame.getContentPane().add(lblLogo);
				
		//Dark blue ribbon on top
		JPanel blueBackground = new JPanel();
		//background color of ribbon should be 0x00829b
		blueBackground.setBackground(new Color(0x00829b));
		blueBackground.setBounds(0, -1, 450, 93);
		frame.getContentPane().add(blueBackground);
		
	}
}
