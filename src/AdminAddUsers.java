import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;

/*
 * 
 * JSON Video 
 * https://www.youtube.com/watch?v=ywLKpHw1MjQ&t=133s
 * 
 */

public class AdminAddUsers {

	/*
	 * PROBLEM
	 * 
	 * Saving entered data into JSON
	 */
	private Random random = new Random();
	private JFrame frame;
	private JTextField textField_Name;
	private JTextField textField_userID;
	private int userIDGenerated;
	private UserTableModel tableModel;
	private JTable table;
	FileWriter file;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAddUsers window = new AdminAddUsers();
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
	public AdminAddUsers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Equipment Management System");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
//-----------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------
		/*
		 * ENTER DATA PANEL
		 */
		JPanel enterData = new JPanel();
		tabbedPane.addTab("", null, enterData, null);
		enterData.setLayout(null);
		
		/*
		 * NAME
		 */
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(57, 49, 55, 16);
		enterData.add(lblName);
		
		textField_Name = new JTextField();
		textField_Name.setText("Please enter teacher's name here");
		textField_Name.setColumns(10);
		textField_Name.setBounds(124, 44, 254, 26);
		enterData.add(textField_Name);
		textField_Name.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e) {
				//When user clicks on the placeholder, the text shown below disappears
				if(textField_Name.getText().trim().equals("Please enter teacher's name here")) {
					textField_Name.setForeground(Color.BLACK);
					textField_Name.setText(""); 
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField_Name.getText().trim().equals("")) {
					//If user did not type anything, when user clicks on different label, text appears in grey again
					textField_Name.setForeground(Color.LIGHT_GRAY);
					textField_Name.setText("Please enter teacher's name here"); 
				}
			}
		});
		
		
		/*
		 * UserID
		 */
		JLabel lblUserID = new JLabel("UserID");
		lblUserID.setBounds(57, 109, 55, 16);
		enterData.add(lblUserID);
		
		textField_userID = new JTextField();
		textField_userID.setColumns(10);
		textField_userID.setBounds(124, 104, 130, 26);
		enterData.add(textField_userID);
		
		textField_userID.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e) {
				//When user clicks on the placeholder, the text shown below disappears
				if(textField_userID.getText().trim().equals("UserID")) {
					textField_userID.setForeground(Color.BLACK);
					textField_userID.setText(""); 
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField_userID.getText().trim().equals("")) {
					//If user did not type anything, when user clicks on different label, text appears in grey again
					textField_userID.setForeground(Color.LIGHT_GRAY);
					textField_userID.setText("UserID"); 
				}
			}
		});
		
		
		
		/*
		 * GENERATE RANDOM UserID
		 */
		JButton btnGenerateID = new JButton("Generate");
		btnGenerateID.setBounds(266, 104, 117, 29);
		enterData.add(btnGenerateID);
		
		btnGenerateID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userIDGenerated = random.nextInt(9999);
				if(userIDGenerated<10) 
				{
					textField_userID.setForeground(Color.BLACK);
					textField_userID.setText("000"+userIDGenerated);
				}
				else if(userIDGenerated<100)
				{
					textField_userID.setForeground(Color.BLACK);
					textField_userID.setText("00"+userIDGenerated);
				}
				else if(userIDGenerated<1000) 
				{
					textField_userID.setForeground(Color.BLACK);
					textField_userID.setText("0"+userIDGenerated);
				}
				else 
				{
					textField_userID.setForeground(Color.BLACK);
					textField_userID.setText(Integer.toString(userIDGenerated));
				}
			}
		});
				
		
		/*
		 * BACK button
		 */
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(Color.DARK_GRAY);
		btnBack.setBounds(0, 194, 72, 26);
		enterData.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close window
				frame.dispose();
				//take user back to admin index screen
				AdminIndex.main(null);
			}
		});
		
		/*
		 * SAVE button
		 */
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(134, 166, 180, 35);
		enterData.add(btnSave);
		
		JLabel lblEnterUserDetails = new JLabel("ENTER USER DETAILS");
		lblEnterUserDetails.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 17));
		lblEnterUserDetails.setBounds(124, 16, 211, 16);
		enterData.add(lblEnterUserDetails);
		
		btnSave.addActionListener(new ActionListener() {
			/*
			 * We want to first check that all 3 fields are not empty
			 * 
			 * If they are filled, add them to UserTableModel
			 * 
			 * Check lines 195-220 in UIMain Monarchs App
			 */
			public void actionPerformed(ActionEvent e) {
				//If one of the fields is empty (that is why we use OR == ||)
				if(	textField_userID.getText().trim().equals("")||
					textField_Name.getText().trim().equals("")) 
				{
					JOptionPane.showMessageDialog(null, "There is an empty field. Please fill all fields to continue.","EMPTY FIELD", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					/*
					 * Read the input in the fields
					 */
					String name = textField_Name.getText();
					String userID = textField_userID.getText();
					
					//Creating the new User from User class
					User newUser = new User(name,userID);
					
					System.out.println(newUser.getName());
					System.out.println(newUser.getUserID());
					
					//Adding the new user to the table model (updating it)
					tableModel.addUser(newUser);
					
					//SAVING the user everytime we add the user
					tableModel.save();
					
					//Switch to second tab — Table displaying users
					tabbedPane.setSelectedIndex(1);
					
					
				}
			}
		});
		
//-----------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------
		
		/*
		 * TABLE MODEL PANEL
		 */
		JPanel DisplayTableModel = new JPanel();
		tabbedPane.addTab("", null, DisplayTableModel, null);
		DisplayTableModel.setLayout(null);
		
		JScrollPane scrollPane_USER = new JScrollPane();
		scrollPane_USER.setBounds(27, 6, 375, 164);
		DisplayTableModel.add(scrollPane_USER);
		
		table = new JTable();
		scrollPane_USER.setViewportView(table);
		
		// Create table inside scroll pane
		table = new JTable();
		
		//creates the table and saves it in the variable "tableModel", so that we can refer to it in the future
		tableModel = new UserTableModel();
		
		//Loading in previous data of the table model
		tableModel.load();
		
		//Set table model onto this table
		table.setModel(tableModel);
		scrollPane_USER.setViewportView(table);
		
		
		//Button to take user to the ADMIN MAIN SCREEN after saving the new user
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close the current window
				frame.dispose();
				
				//Take user to Admin Index Main Screen
				AdminIndex.main(null);
			}
		});
		btnHome.setBounds(156, 182, 117, 29);
		DisplayTableModel.add(btnHome);
	}
}
