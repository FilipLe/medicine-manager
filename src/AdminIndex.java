import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;

public class AdminIndex extends HomeScreen{

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
		time();
		date();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Medicine Manager — Admin Index Screen");
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
		 * Option to create user
		 */
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close current screen
				frame.dispose();
				//Take to create user screen
				AdminAddUserScreen.main(null);
			}
		});
		btnCreateUser.setBounds(54, 122, 344, 39);
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
		btnEditUsers.setBounds(54, 161, 344, 39);
		frame.getContentPane().add(btnEditUsers);
		
		
		/*
		 * Equipment database
		 */
		JButton btnAccessEquipmentDatabase = new JButton("Access Medication Database");
		btnAccessEquipmentDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close current frame
				frame.dispose();
				
				//open the Equipment tree
				EquipmentDatabase.main(null);
			}
		});
		btnAccessEquipmentDatabase.setBounds(54, 202, 344, 39);
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
		lblSelectAction.setBounds(18, 100, 241, 16);
		frame.getContentPane().add(lblSelectAction);
	}

}
