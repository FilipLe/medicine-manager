import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class AdminIndex {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminIndex window = new AdminIndex();
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
	public AdminIndex() {
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
		 * Option to create user
		 */
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close current screen
				frame.dispose();
				//Take to create user screen
				AdminAddUsers.main(null);
			}
		});
		btnCreateUser.setBounds(18, 26, 412, 53);
		frame.getContentPane().add(btnCreateUser);
		
		
		/*
		 * Option to edit users
		 */
		JButton btnEditUsers = new JButton("Edit User");
		btnEditUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close current screen
				frame.dispose();
				//take to edit user screen
				AdminEditUser.main(null);
			}
		});
		btnEditUsers.setBounds(18, 100, 412, 53);
		frame.getContentPane().add(btnEditUsers);
		
		
		/*
		 * Equipment database
		 */
		JButton btnAccessEquipmentDatabase = new JButton("Access Equipment Database");
		btnAccessEquipmentDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close current frame
				frame.dispose();
				
				//open the Equipment tree
				EquipmentDatabase.main(null);
			}
		});
		btnAccessEquipmentDatabase.setBounds(18, 166, 412, 53);
		frame.getContentPane().add(btnAccessEquipmentDatabase);
		
		/*
		 * Logout option
		 */
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close current window
				frame.dispose();
				//Take user to main screen
				UIMain.main(null);
			}
		});
		btnLogout.setForeground(Color.DARK_GRAY);
		btnLogout.setBounds(6, 240, 72, 26);
		frame.getContentPane().add(btnLogout);
		
		/*
		 * Text "Select Action"
		 */
		JLabel lblSelectAction = new JLabel("Select Action:");
		lblSelectAction.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblSelectAction.setBounds(18, 6, 241, 16);
		frame.getContentPane().add(lblSelectAction);
	}

}
