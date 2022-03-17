import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JPanel;

public class AdminEditUser {

	/*
	 * Admin Edit User Screen
	 * 
	 * WORK ON DELETING USER LINES 120-147
	 * 
	 * Lines 141-156 —-> Deleting users
	 */
	private JFrame frame;
	private JTable table;
	private UserTableModel tableModel;
	private JTextField textFieldName;
	private JTextField textFieldID;
	
	//Variables that will be accessible across different classes (will be accessed in Edit User Screen)
	public static String currentName;
	public static String currentID;

	//The user 
	private User user;

	//Store the position of the being edited user
	int positionOfUser;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminEditUser window = new AdminEditUser();
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
	public AdminEditUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Equipment Management System");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		 * Edit button
		 */
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If the fields is empty, we cannot edit user
				if(textFieldName.getText().equals("") || textFieldID.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please fill in every field","Please fill in every field", JOptionPane.ERROR_MESSAGE);
				}
				else {
					//Close current window
					frame.dispose();
					
					//Take user to edit user screen
					EditUserScreen.main(null);
				}
			}
		});
		btnEdit.setBounds(276, 147, 152, 29);
		frame.getContentPane().add(btnEdit);
		
		/*
		 * DELETE button
		 */
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If the fields is empty, we cannot delete user
				if(textFieldName.getText().equals("") || textFieldID.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please fill in every field","Please fill in every field", JOptionPane.ERROR_MESSAGE);
				}
				else {
					//Load up the table model
					tableModel = new UserTableModel();
					tableModel.load();
					
					/*
					 * Delete the user
					 */				
					//Amount of users
					int tableSize = tableModel.getRowCount();
					
					//Counter to loop through list of users to check if user exists
					int counter = 0;
					
					//Status checker for the main loop
					boolean exist = false;		
					
					//While loop to check if user exists
					while(exist == false && counter < tableSize) {
						//Get User by index
						user = tableModel.getUser(counter);
						
						//Finding the user's position in the table model
						if(AdminEditUser.currentName.equals(user.getName())) {	
							
							//End the loop
							exist = true;
							
							//Store the position of that user (to delete it later)
							positionOfUser = counter;
						}
						counter ++;
					}
					//Access user at this position and replace them with new data
					//Remove the old user
					//Add new user
					
					//Accessing user's position
					System.out.println("User at: "+ positionOfUser);
					
					//Removing user at that position
					tableModel.removeRowAt(positionOfUser);
					
					//Save
					tableModel.save();
					
					//Go back to main screen to Admin
					frame.dispose();
					AdminIndex.main(null);
				}
			}
		});
		btnDelete.setBounds(276, 199, 152, 29);
		frame.getContentPane().add(btnDelete);
		
		/*
		 * Back button
		 */
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close window
				frame.dispose();
				
				//Take user to Admin Index Screen
				AdminIndex.main(null);
			}
		});
		btnBack.setForeground(Color.DARK_GRAY);
		btnBack.setBounds(6, 240, 72, 26);
		frame.getContentPane().add(btnBack);
		
		/*
		 * Scroll Pane to display users
		 */
		JScrollPane scrollPane_USERS = new JScrollPane();
		scrollPane_USERS.setBounds(6, 6, 233, 222);
		frame.getContentPane().add(scrollPane_USERS);
		
		// Create table inside scroll pane
		table = new JTable();
		
		//creates the table and saves it in the variable "tableModel", so that we can refer to it in the future
		tableModel = new UserTableModel();
		
		//Loading in previous data of the table model
		tableModel.load();
		
		//Set table model onto this table
		table.setModel(tableModel);
		scrollPane_USERS.setViewportView(table);
		
		/*
		 * USER INFORMATION Title
		 */
		JLabel lblUserInformation = new JLabel("User Information:");
		lblUserInformation.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 12));
		lblUserInformation.setBounds(251, 7, 162, 16);
		frame.getContentPane().add(lblUserInformation);
		
		
		/*
		 * USER'S NAME
		 */
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblUserName.setBounds(251, 43, 86, 16);
		frame.getContentPane().add(lblUserName);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		textFieldName.setColumns(10);
		textFieldName.setBounds(317, 43, 111, 16);
		frame.getContentPane().add(textFieldName);
		
		
		/*
		 * USER'S ID
		 */
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblUserId.setBounds(251, 87, 86, 16);
		frame.getContentPane().add(lblUserId);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		textFieldID.setColumns(10);
		textFieldID.setBounds(298, 87, 130, 16);
		frame.getContentPane().add(textFieldID);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(246, 6, 187, 116);
		frame.getContentPane().add(panel);
		
		
		
		/*
		 * Selecting user from the table to Edit or Delete
		 */
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//Finding the row index of the row pressed
				int rowIndex = table.getSelectedRow();
				
				//Ignore this value if this isn't the final value
				//So it does the event below once, instead of twice when a row is clicked
				//If the row index is greater than 0, then the selected row is valid
				//And also if this particular selection is the final selection, then we display info
				if(rowIndex >= 0 && !e.getValueIsAdjusting()) {
					
					//Get User at the clicked position
					User user = tableModel.getUser(rowIndex);
					
					//Display selected user's name
					textFieldName.setText(user.getName());
					
					//Display selected user's number
					textFieldID.setText(String.valueOf(user.getUserID()));
					
					
					//Assigning to variables to display them in the next EDIT screen at the "OLD info" section
					currentName = user.getName();
					currentID = String.valueOf(user.getUserID());
				}
			}
		});
	}
}
