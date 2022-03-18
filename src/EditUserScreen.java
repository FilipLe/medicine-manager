import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class EditUserScreen {

	private JFrame frame;
	private Random random = new Random();
	private JTextField textField_NewName;
	private JTextField textField_NewID;
	private int userID;
	private JTextField textField_oldName;
	private JTextField textField_oldID;
	private UserTableModel tableModel;
	private User user;

	//Store the position of the being edited user
	int positionOfUser;
	
	/*
	 * LINES 188 - 205
	 */
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditUserScreen window = new EditUserScreen();
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
	public EditUserScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*
		 * Title
		 */
		frame = new JFrame("Medicine Manager — Edit User");
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		 * Subheading
		 */
		JLabel lblEditUserDetail = new JLabel("EDIT USER INFORMATION");
		lblEditUserDetail.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 17));
		lblEditUserDetail.setBounds(123, 20, 241, 16);
		frame.getContentPane().add(lblEditUserDetail);
		
		
		
		/*
		 * OLD USER NAME
		 */
		JLabel lbl_oldName = new JLabel("Old Name");
		lbl_oldName.setBounds(74, 48, 72, 16);
		frame.getContentPane().add(lbl_oldName);
		
		textField_oldName = new JTextField();
		textField_oldName.setColumns(10);
		textField_oldName.setBounds(158, 43, 252, 26);
		frame.getContentPane().add(textField_oldName);
		
		//Display the old name of the selected user in "AdminEditUser.java"
		textField_oldName.setText(AdminEditUser.currentName);
		
		
		
		/*
		 * OLD USER ID
		 */
		JLabel lbl_oldID = new JLabel("Old ID");
		lbl_oldID.setBounds(74, 87, 72, 16);
		frame.getContentPane().add(lbl_oldID);
		
		textField_oldID = new JTextField();
		textField_oldID.setColumns(10);
		textField_oldID.setBounds(158, 82, 252, 26);
		frame.getContentPane().add(textField_oldID);
		
		//Display the old ID of the selected user in "AdminEditUser.java"
		textField_oldID.setText(AdminEditUser.currentID);
		
		
		
		/*
		 * New Name 
		 */
		JLabel lblNewName = new JLabel("New Name");
		lblNewName.setBounds(74, 130, 72, 16);
		frame.getContentPane().add(lblNewName);
		
		textField_NewName = new JTextField();
		textField_NewName.setColumns(10);
		textField_NewName.setBounds(158, 125, 252, 26);
		frame.getContentPane().add(textField_NewName);
		
		
		/*
		 * New User ID
		 */
		JLabel lblNewId = new JLabel("New ID");
		lblNewId.setBounds(74, 173, 72, 16);
		frame.getContentPane().add(lblNewId);
		
		textField_NewID = new JTextField();
		textField_NewID.setColumns(10);
		textField_NewID.setBounds(158, 168, 130, 26);
		frame.getContentPane().add(textField_NewID);
		
		
		/*
		 * Generate new random ID for USER
		 */
		JButton btnGenerateID = new JButton("Generate");
		btnGenerateID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userID = random.nextInt(9999);
				if(userID<10) 
				{
					textField_NewID.setForeground(Color.BLACK);
					textField_NewID.setText("000"+userID);
				}
				else if(userID<100)
				{
					textField_NewID.setForeground(Color.BLACK);
					textField_NewID.setText("00"+userID);
				}
				else if(userID<1000) 
				{
					textField_NewID.setForeground(Color.BLACK);
					textField_NewID.setText("0"+userID);
				}
				else 
				{
					textField_NewID.setForeground(Color.BLACK);
					textField_NewID.setText(Integer.toString(userID));
				}
			}
		});
		btnGenerateID.setBounds(293, 165, 117, 29);
		frame.getContentPane().add(btnGenerateID);
		
		
		/*
		 * Back button
		 */
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close current window
				frame.dispose();
				
				//Go back to EditUser table
				AdminEditUser.main(null);
			}
		});
		btnBack.setForeground(Color.DARK_GRAY);
		btnBack.setBounds(6, 240, 72, 26);
		frame.getContentPane().add(btnBack);
		
		/**
		 * DOESN'T WORK YET
		 */
		/*
		 * SAVE button
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			//Access the user with the current name and id
			public void actionPerformed(ActionEvent e) {
				if(textField_NewName.getText().equals("") || textField_NewID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in every field","Please fill in every field", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
					/**
					 * To edit user detail, we delete them and and add new details
					 */
					
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
					
					//Add new user (replace)
					String newname = textField_NewName.getText();
					String newuserID = textField_NewID.getText();
					User newUser = new User(newname,newuserID);
					tableModel.addUser(newUser);
					
					tableModel.save();
					
					System.out.println("Saved");
					
					//Take back to edit user screen
					//Close this window
					frame.dispose();
					
					//Run admin edit user screen
					AdminEditUser.main(null);
				}
				
			}
		});
		btnSave.setBounds(123, 206, 180, 35);
		frame.getContentPane().add(btnSave);
	}

}
