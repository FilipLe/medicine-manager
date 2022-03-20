import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class UserLoginScreen extends HomeScreen {
	
	/*
	 * User LOGIN SCREEN
	 */

	private JFrame frame;
	private JTextField userID;
	private JTextField userNAME;
	private UserTableModel tableModel;
	public static String loggedInUser;
	public static String loggedInUserID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLoginScreen window = new UserLoginScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserLoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Medicine Manager — User Login Screen");
		//Frame light blue background
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
				
		/*
		 * --------------------------------------------------------------------------------
		 */
				
		/*
		 * TIME
		 */
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
				
				
		/*
		 * --------------------------------------------------------------------------------
		 */
				
		/*
		 * DESIGN
		 */
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
		
		/*
		 * TeacherID field
		 */
		JLabel lblTeacherID = new JLabel("User ID");
		lblTeacherID.setBounds(73, 190, 72, 16);
		frame.getContentPane().add(lblTeacherID);
		
		userID = new JTextField();
		userID.setColumns(10);
		userID.setBounds(156, 185, 232, 26);
		frame.getContentPane().add(userID);
		
		userID.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e) {
				//When user clicks on the placeholder, the text shown below disappears
				if(userID.getText().trim().equals("Please enter userID here")) {
					userID.setForeground(Color.BLACK);
					userID.setText(""); 
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(userID.getText().trim().equals("")) {
					//If user did not type anything, when user clicks on different label, text appears in grey again
					userID.setForeground(Color.LIGHT_GRAY);
					userID.setText("Please enter userID here"); 
				}
			}
		});
		
		
		/*
		 * Clear Button
		 */
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userNAME.setText(null);
				userID.setText(null); 
			}
		});
		clearButton.setBounds(84, 223, 117, 29);
		frame.getContentPane().add(clearButton);
		
		
		
		/*
		 * Login Button
		 */
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			//We need to load in JSON data and check if user input match with data entered
			public void actionPerformed(ActionEvent e) {
				
				//What happens if fields empty
				if(userNAME.getText().equals("") || userID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in every field","Please fill in every field", JOptionPane.ERROR_MESSAGE);
				}
				else {
					//Getting the user inputs
					String userName = userNAME.getText();
					String id = userID.getText();
					
					//Accessing the table model
					tableModel = new UserTableModel();
					tableModel.load();
					
					//Amount of users
					int tableSize = tableModel.getRowCount();
					
					//Counter to loop through list of users to check if user exists
					int counter = 0; 
					
					//Status checker for the main loop
					boolean exist = false;		
					
					//While loop to check if user exists
					while(exist == false && counter < tableSize) {
						//Get User by index
						User user = tableModel.getUser(counter);
					
						//If both info of user exists in json table model
						
						/*
						 * CHANGE THIS CODE TO CHECK FIND USERID AND NAME BUT WITH NEW SAVE METHOD
						 */
						if(userName.equals(user.getName()) && id.equals(user.getUserID())) {	
							//End the loop
							exist = true;
							
							//Save the user's name so that later we know who to access data
							loggedInUser = userName;
							
							//Save the user's ID (unique number) so that later we know who to access data
							loggedInUserID = id;
							
							//Empty field entries
							userNAME.setText(null);
							userID.setText(null);
							
							//Message Dialog Box
							JOptionPane.showMessageDialog(null, "Login Successful!","Login Successful", JOptionPane.INFORMATION_MESSAGE);
							
							//Close current window
							frame.dispose();
							
							//Take user to intake screen
							MedicationUsers.main(null);
						}			
						//Increment by 1 to move to next user
						counter++;
					}
					
					
					//if they don't exist in table model, "exist" would still be false after looping through users
					if(exist == false) {
						userNAME.setText(null);
						userID.setText(null);
						JOptionPane.showMessageDialog(null, "Incorrect ID or password","Incorrect ID or password", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnLogin.setBounds(243, 223, 117, 29);
		frame.getContentPane().add(btnLogin);
		
		/*
		 * Logging in using ENTER KEY
		 */
		userID.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//What happens if fields empty
				if(userNAME.getText().equals("") || userID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in every field","Please fill in every field", JOptionPane.ERROR_MESSAGE);
				}
				else {
					//Getting the user inputs
					String userName = userNAME.getText();
					String id = userID.getText();
					
					//Accessing the table model
					tableModel = new UserTableModel();
					tableModel.load();
					
					//Amount of users
					int tableSize = tableModel.getRowCount();
					
					//Counter to loop through list of users to check if user exists
					int counter = 0; 
					
					//Status checker for the main loop
					boolean exist = false;		
					
					//While loop to check if user exists
					while(exist == false && counter < tableSize) {
						//Get User by index
						User user = tableModel.getUser(counter);
					
						//If both info of user exists in json table model
						if(userName.equals(user.getName()) && id.equals(user.getUserID())) {	
							//End the loop
							exist = true;
							
							//Save the user's name so that later we know who to access data
							loggedInUser = userName;
							
							//Save the user's ID (unique number) so that later we know who to access data
							loggedInUserID = id;
							
							
							//Empty field entries
							userNAME.setText(null);
							userID.setText(null);
							
							//Message Dialog Box
							JOptionPane.showMessageDialog(null, "Login Successful!","Login Successful", JOptionPane.INFORMATION_MESSAGE);
							
							//Close current window
							frame.dispose();
							
							//Take user to intake screen
							MedicationUsers.main(null);
						}			
						//Increment by 1 to move to next user
						counter++;
					}
					
					
					//if they don't exist in table model, "exist" would still be false after looping through users
					if(exist == false) {
						userNAME.setText(null);
						userID.setText(null);
						JOptionPane.showMessageDialog(null, "Incorrect ID or password","Incorrect ID or password", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		/*
		 * Teacher Name Field
		 */
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(73, 140, 72, 16);
		frame.getContentPane().add(lblName);
		
		userNAME = new JTextField();
		userNAME.setColumns(10);
		userNAME.setBounds(156, 135, 232, 26);
		frame.getContentPane().add(userNAME);
		
		userNAME.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e) {
				//When user clicks on the placeholder, the text shown below disappears
				if(userNAME.getText().trim().equals("Please enter your name here")) {
					userNAME.setForeground(Color.BLACK);
					userNAME.setText(""); 
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(userNAME.getText().trim().equals("")) {
					//If user did not type anything, when user clicks on different label, text appears in grey again
					userNAME.setForeground(Color.LIGHT_GRAY);
					userNAME.setText("Please enter your name here"); 
				}
			}
		});
		
		/*
		 * Back Button
		 */
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close current window
				frame.dispose();
				//take user to main screen
				HomeScreen.main(null);
			}
		});
		btnBack.setForeground(Color.DARK_GRAY);
		btnBack.setBounds(0, 240, 72, 26);
		frame.getContentPane().add(btnBack);
		
		//TITLE
		JLabel lblUserLoginScreen = new JLabel("USER LOGIN SCREEN");
		lblUserLoginScreen.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 17));
		lblUserLoginScreen.setBounds(149, 104, 211, 16);
		frame.getContentPane().add(lblUserLoginScreen);
		
		/*
		 * ICONS
		 */
		//Admin ID Icon
		JLabel lblIconID = new JLabel(new ImageIcon("/Users/nguyenle/workspace-2021-swing-applications/Medicine_Manager_REAL/src/adminIDIcon.png"));
		lblIconID.setBounds(25, 121, 42, 55);
		frame.getContentPane().add(lblIconID);
		
		//Password ID Icon
		JLabel lblPasswordIcon = new JLabel(new ImageIcon("/Users/nguyenle/workspace-2021-swing-applications/Medicine_Manager_REAL/src/passwordIcon.png"));
		lblPasswordIcon.setBounds(25, 170, 42, 55);
		frame.getContentPane().add(lblPasswordIcon);
	}
}
