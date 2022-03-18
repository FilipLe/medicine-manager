import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.Icon;

/**
 * 
 * @author nguyenle
 *
 * Admin Login Screen
 */

//We do extend so that we can use the time() method in this class as well
public class AdminLoginScreen extends HomeScreen {

	private JFrame frame;
	private JTextField adminID;
	private JPasswordField adminPassword;
	private String ID = "0";
	private String loginPassword = "onetwothree";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLoginScreen window = new AdminLoginScreen();
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
	public AdminLoginScreen() {
		initialize();
		time();
		date();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Home Medication Tracker - Admin Login");
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
		 * ------------------------------------------------------------------------------------------------
		 */
		
		/*
		 * ADMIN ID
		 */
		JLabel lblAdminID = new JLabel("AdminID");
		lblAdminID.setBounds(70, 140, 72, 16);
		frame.getContentPane().add(lblAdminID);
	
		adminID = new JTextField();
		adminID.setColumns(10);
		adminID.setBounds(140, 135, 232, 26);
		frame.getContentPane().add(adminID);
		//Place to input Admin ID
		adminID.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e) {
				//When user clicks on the placeholder, the text shown below disappears
				if(adminID.getText().trim().equals("Please enter AdminID here")) {
					adminID.setForeground(Color.BLACK);
					adminID.setText(""); 
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(adminID.getText().trim().equals("")) {
					//If user did not type anything, when user clicks on different label, text appears in grey again
					adminID.setForeground(Color.LIGHT_GRAY);
					adminID.setText("Please enter AdminID here"); 
				}
			}
		});
		
		/*
		 * ------------------------------------------------------------------------------------------------
		 */
		
		/*
		 * PASSWORD
		 */
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(70, 189, 61, 16);
		frame.getContentPane().add(lblPassword);
		
		adminPassword = new JPasswordField();
		adminPassword.setBounds(140, 184, 232, 26);
		frame.getContentPane().add(adminPassword);
		
		/*
		 * Encrypting password when input
		 * Decrypting reminder message when field is empty
		 */
		adminPassword.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e) {
				//When user clicks on the placeholder, the text shown below disappears
				//When user writes, their input will be encrypted in the form of '•'
				if(adminPassword.getText().trim().equals("Please enter password")) {
					adminPassword.setForeground(Color.BLACK);
					adminPassword.setEchoChar ((char) '•');
					adminPassword.setText(""); 
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(adminPassword.getText().trim().equals("")) {
					//If user did not type anything, when user clicks on different label, text below appears in grey again
					adminPassword.setForeground(Color.LIGHT_GRAY);
					//Display the message below in text form 
					adminPassword.setEchoChar ((char) 0);
					adminPassword.setText("Please enter password"); 
				}
			}
		});
		
		/*
		 * ------------------------------------------------------------------------------------------------
		 */
		
		//TITLE
		JLabel lblAdminLoginScreen = new JLabel("ADMIN LOGIN SCREEN");
		lblAdminLoginScreen.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 17));
		lblAdminLoginScreen.setBounds(126, 104, 211, 16);
		frame.getContentPane().add(lblAdminLoginScreen);
		
		//Clear Fields Button
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminID.setText(null);
				adminPassword.setText(null);
			}
		});
		clearButton.setBounds(97, 223, 117, 29);
		frame.getContentPane().add(clearButton);
		
		/*
		 * ------------------------------------------------------------------------------------------------
		 */
		//Login Button
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AdminID = adminID.getText();
				String password = adminPassword.getText();
				
				/*
				 * If one of the fields are empty, prompt a message
				 */
				if(AdminID.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in every field","Please fill in every field", JOptionPane.ERROR_MESSAGE);
				}
				
				/*
				 * Correct user and password
				 */
				else if(AdminID.equals(ID) && password.equals(loginPassword)) 
				{
					//Empty field entries
					adminID.setText(null);
					adminPassword.setText(null);
					
					//Message Dialog Box
					JOptionPane.showMessageDialog(null, "Login Successful!","Login Successful", JOptionPane.INFORMATION_MESSAGE);
					
					//Close current window
					frame.dispose();
					
					//Take user to Admin Index Screen
					AdminIndex.main(null);
				}
				
				/*
				 * Incorrect user and password
				 */
				else 
				{
					JOptionPane.showMessageDialog(null, "Incorrect ID or password","Incorrect ID or password", JOptionPane.ERROR_MESSAGE);
					adminID.setText(null);
					adminPassword.setText(null);
				}
			}
		});
		btnLogin.setBounds(245, 223, 117, 29);
		frame.getContentPane().add(btnLogin);
		
		/*
		 * ------------------------------------------------------------------------------------------------
		 */
		
		/*
		 * If ENTER key is pressed login, instead of pressing Login Button
		 *
		 */
		adminPassword.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AdminID = adminID.getText();
				String password = adminPassword.getText();
				
				/*
				 * If one of the fields are empty, prompt a message
				 */
				if(AdminID.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in every field","Please fill in every field", JOptionPane.ERROR_MESSAGE);
				}
				
				/*
				 * Correct user and password
				 */
				else if(AdminID.equals(ID) && password.equals(loginPassword)) 
				{
					//Empty field entries
					adminID.setText(null);
					adminPassword.setText(null);
					
					//Message Dialog Box
					JOptionPane.showMessageDialog(null, "Login Successful!","Login Successful", JOptionPane.INFORMATION_MESSAGE);
					
					//Close current window
					frame.dispose();
					
					//Take user to Admin Index Screen
					AdminIndex.main(null);
				}
				
				/*
				 * Incorrect user and password
				 */
				else 
				{
					JOptionPane.showMessageDialog(null, "Incorrect ID or password","Incorrect ID or password", JOptionPane.ERROR_MESSAGE);
					adminID.setText(null);
					adminPassword.setText(null);
				}
			}
		});
		
		/*
		 * ------------------------------------------------------------------------------------------------
		 */
		
		//Back Button
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close current window
				frame.dispose();
				//Take user to main screen
				HomeScreen.main(null);
			}
		});
		btnBack.setForeground(Color.DARK_GRAY);
		btnBack.setBounds(6, 240, 72, 26);
		frame.getContentPane().add(btnBack);
		
		/*
		 * ------------------------------------------------------------------------------------------------
		 */
		
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
