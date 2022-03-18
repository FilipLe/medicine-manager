import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.SwingConstants;

/**
 * 
 * @author nguyenle
 *
 * Admin Screen to Add User
 */
public class AdminAddUserScreen {
	private Random random = new Random();
	private JFrame frame;
	private JTextField textField_Name;
	private JTextField txtEnterAge;
	private JTextField textField_userID;
	private int userIDGenerated;
	private UserTableModel tableModel;
	FileWriter file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAddUserScreen window = new AdminAddUserScreen();
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
	public AdminAddUserScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Home Medication Tracker - Add User Screen");
		//Frame light blue background
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Logo Icon
		JLabel lblLogo = new JLabel(new ImageIcon("/Users/nguyenle/workspace-2021-swing-applications/Medicine_Manager_REAL/src/NguyenLogo.png"));
		lblLogo.setBounds(3, 0, 100, 93);
		frame.getContentPane().add(lblLogo);
		
		JLabel lblEnterUserDetails = new JLabel("ENTER USER DETAILS");
		lblEnterUserDetails.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 17));
		lblEnterUserDetails.setBounds(117, 104, 209, 16);
		frame.getContentPane().add(lblEnterUserDetails);
		
		
		JLabel lblQuote1 = new JLabel("Spend less time thinking about medications");
		lblQuote1.setFont(new Font("Apple Chancery", Font.BOLD | Font.ITALIC, 15));
		lblQuote1.setBounds(115, 6, 276, 45);
		frame.getContentPane().add(lblQuote1);
		
		JLabel lblQuote2 = new JLabel("— and more time enjoying life");
		lblQuote2.setFont(new Font("Apple Chancery", Font.BOLD | Font.ITALIC, 15));
		lblQuote2.setBounds(238, 35, 191, 45);
		frame.getContentPane().add(lblQuote2);
		
		
		
		//Dark blue ribbon on top
		JPanel blueBackground = new JPanel();
		//background color of ribbon should be 0x00829b
		blueBackground.setBackground(new Color(0x00829b));
		blueBackground.setBounds(0, -1, 450, 93);
		frame.getContentPane().add(blueBackground);
		
		//Button Back --> Take Admin to Index Screen
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AdminIndex.main(null);
			}
		});
		btnBack.setForeground(Color.DARK_GRAY);
		btnBack.setBounds(3, 242, 72, 26);
		frame.getContentPane().add(btnBack);

		
		//Input Name
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(63, 139, 55, 16);
		frame.getContentPane().add(lblName);
		
		textField_Name = new JTextField();
		textField_Name.setForeground(Color.LIGHT_GRAY);
		textField_Name.setText("Please enter user's name here");
		textField_Name.setColumns(10);
		textField_Name.setBounds(127, 134, 258, 26);
		frame.getContentPane().add(textField_Name);
		textField_Name.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e) {
				//When user clicks on the placeholder, the text shown below disappears
				if(textField_Name.getText().trim().equals("Please enter user's name here")) {
					textField_Name.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					textField_Name.setForeground(Color.BLACK);
					textField_Name.setText(""); 
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField_Name.getText().trim().equals("")) {
					//If user did not type anything, when user clicks on different label, text appears in grey again
					textField_Name.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					textField_Name.setForeground(Color.LIGHT_GRAY);
					textField_Name.setText("Please enter user's name here"); 
				}
			}
		});
		
		
		
		//Input Age
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(63, 177, 55, 16);
		frame.getContentPane().add(lblAge);
		
		txtEnterAge = new JTextField();
		txtEnterAge.setForeground(Color.LIGHT_GRAY);
		txtEnterAge.setText("Please enter user's age here");
		txtEnterAge.setColumns(10);
		txtEnterAge.setBounds(127, 172, 258, 26);
		frame.getContentPane().add(txtEnterAge);
		
		txtEnterAge.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e) {
				//When user clicks on the placeholder, the text shown below disappears
				if(txtEnterAge.getText().trim().equals("Please enter user's age here")) {
					txtEnterAge.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					txtEnterAge.setForeground(Color.BLACK);
					txtEnterAge.setText(""); 
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtEnterAge.getText().trim().equals("")) {
					//If user did not type anything, when user clicks on different label, text appears in grey again
					txtEnterAge.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					txtEnterAge.setForeground(Color.LIGHT_GRAY);
					txtEnterAge.setText("Please enter user's age here"); 
				}
			}
		});
		
		//creates the table and saves it in the variable "tableModel", so that we can refer to it in the future
		tableModel = new UserTableModel();
		
		//Loading in previous data of the table model
		tableModel.load();
		
		
		//Button save details
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If one of the fields is empty (that is why we use OR == ||)
				if(	textField_userID.getText().trim().equals("0000")||
					textField_Name.getText().trim().equals("Please enter user's name here")||
					txtEnterAge.getText().trim().equals("Please enter user's age here")) 
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
					
					//Close the current window
					frame.dispose();
					
					//Take user to Admin Index Main Screen
					AdminIndex.main(null);
					
				}
			}
		});
		btnSave.setBounds(225, 240, 147, 31);
		frame.getContentPane().add(btnSave);
	
		/*
		 * Help Info Icons
		 */
		//Import image
		ImageIcon imageIcon = new ImageIcon("/Users/nguyenle/workspace-2021-swing-applications/Medicine_Manager_REAL/src/information_icon.png"); 
		Image image = imageIcon.getImage();
		//Rescale image (width, height, type scale)
		Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
		
		
		//Name
		JLabel lblHelpInfoNameIcon = new JLabel(new ImageIcon(newimg));
		lblHelpInfoNameIcon.setBounds(378, 134, 42, 26);
		frame.getContentPane().add(lblHelpInfoNameIcon);
		//MouseListener --> Whether the JLabel has been clicked
		lblHelpInfoNameIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	JOptionPane.showMessageDialog(null, "What is the user's first name?"
            			+ "\n\nThe user's name must be a String (text) "
            			+ "\nCannot be of any other form such as numbers or symbols"
            			+ "\n\nPlease enter user's name in text form","Help", JOptionPane.INFORMATION_MESSAGE);
            }
        });
		
		
		//Age
		JLabel lblHelpInfoAgeIcon = new JLabel(new ImageIcon(newimg));
		lblHelpInfoAgeIcon.setBounds(378, 171, 42, 26);
		frame.getContentPane().add(lblHelpInfoAgeIcon);
		lblHelpInfoAgeIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	JOptionPane.showMessageDialog(null, "What is the user's age?"
            			+ "\n\nThe age is important in determining the drug administration interval because as we age,"
            			+ "\nchanges in our body can affect the way medications are absorbed and utilized."
            			+ "\n\nThese changes can lead to drugs having a longer duration of action and increased effect."
            			+ "\n\nDrugs that were effective may become compounded and overexceed their therapeutic"
            			+ "\nthreshhold causing increased side effects."
            			+ "\n\nThe user's age must be a whole number."
            			+ "\nCannot be of any other forms such as Strings, characters, symbols, etc.","HELP — Input Age", JOptionPane.INFORMATION_MESSAGE);
            }
        });
		
		
		/*
		 * Generate Random User ID (for login reasons)
		 */
		JLabel lblGenerateUserId = new JLabel("Generate Random User ID");
		lblGenerateUserId.setBounds(63, 215, 165, 16);
		frame.getContentPane().add(lblGenerateUserId);
		
		JLabel lblHelpInfoGenerateUserID = new JLabel(new ImageIcon(newimg));
		lblHelpInfoGenerateUserID.setBounds(378, 208, 42, 26);
		frame.getContentPane().add(lblHelpInfoGenerateUserID);
		lblHelpInfoGenerateUserID.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	JOptionPane.showMessageDialog(null, "Press 'Generate' button to create the current user an ID"
            			+ "\n\nThis ID will be user's unique key used as an identification key (password) for logging in later"
            			+ "\n\nAn auto-generated ID will be used instead of allowing user to choose password"
            			+ "\nto avoid added complexities and risks.","HELP — Generate User ID", JOptionPane.INFORMATION_MESSAGE);
            }
        });
		
		//Where generated UserID will be shown
		textField_userID = new JTextField();
		textField_userID.setHorizontalAlignment(SwingConstants.CENTER);
		textField_userID.setText("0000");
		textField_userID.setForeground(Color.LIGHT_GRAY);
		textField_userID.setColumns(10);
		textField_userID.setBounds(238, 210, 61, 26);
		frame.getContentPane().add(textField_userID);
		
		textField_userID.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e) {
				//When user clicks on the placeholder, the text shown below disappears
				if(textField_userID.getText().trim().equals("0000")) {
					textField_userID.setForeground(Color.BLACK);
					textField_userID.setText(""); 
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField_userID.getText().trim().equals("")) {
					//If user did not type anything, when user clicks on different label, text appears in grey again
					textField_userID.setForeground(Color.LIGHT_GRAY);
					textField_userID.setText("0000"); 
				}
			}
		});
		
		
		//Generate button
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
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
		btnGenerate.setBounds(298, 208, 93, 29);
		frame.getContentPane().add(btnGenerate);
		
		//Clear all textfields button
		JButton btnClearEntries = new JButton("Clear Entries");
		btnClearEntries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * FIX THIS: Instead of erasing everything, set Text to their default grey texts
				 */
				textField_Name.setForeground(Color.LIGHT_GRAY);
				textField_Name.setText("Please enter user's name here");
				
				txtEnterAge.setForeground(Color.LIGHT_GRAY);
				txtEnterAge.setText("Please enter user's age here");
				
				textField_userID.setForeground(Color.LIGHT_GRAY);
				textField_userID.setText("0000");
				
			}
		});
		btnClearEntries.setBounds(97, 241, 131, 31);
		frame.getContentPane().add(btnClearEntries);
		
		
	}
}
