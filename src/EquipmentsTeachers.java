import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

public class EquipmentsTeachers {

	/*
	 * TEACHER'S BORROWING SCREEN
	 */
		
	private JFrame frame;
	private JTextField textBorrowStatus;
	private JTextField textField_EquipmentType;
	private JTextField textField_ITEM_ID;
	public boolean available;
	private EquipmentTableModel tableModel;
	private EquipmentClass clickedEquipment;
	private String currentUser;
	private String currentUserID;
	private String selectedEquipmentID;
	private String selectedEquipmentType;
	private int clickedItemPosition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EquipmentsTeachers window = new EquipmentsTeachers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Method to check if something is an integer
	 */
	public boolean isInteger( String input ) {
	    try {
	        Integer.parseInt( input );
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}
	
	/**
	 * Tree Listener — Doing something when user clicks
	 */
	private TreeSelectionListener createSelectionListener() {
        return new TreeSelectionListener() {
        	//When user clicks on different node
            public void valueChanged(TreeSelectionEvent e) {
            	
            	/*
            	 * Finding path of clicked node
            	 */
                TreePath path = e.getPath();
                int pathCount = path.getPathCount();
      
                
                //Printing the whole path of the node clicked       
                for (int i = 1; i < pathCount; i++) {
                    System.out.print(path.getPathComponent(i).toString());
                    if (i + 1 != pathCount) {
                        System.out.print("-->");
                    }
                }
                System.out.println("\n");
                
                
                //Accessing the outer most Child node ==> The Item ID
                String itemId = path.getPathComponent(pathCount-1).toString();
                
                //Accessing the second outer most child node ==> equipment type
                String equipmentType = path.getPathComponent(pathCount-2).toString();
                
                
                //Only if the item Id clicked is an integer, we display on the information board
                if(isInteger(itemId) == true) {
                	//Loading in JSON to check list of equipments and their properties
                	tableModel = new EquipmentTableModel();
					tableModel.load();
					
					//Looping through the equipments until we find the right one
					int tableSize = tableModel.getRowCount();
					int counter = 0;
					boolean found = false;
					while(found == false && counter < tableSize) {
						EquipmentClass equipment = tableModel.getEquipment(counter);
						if(itemId.equals(equipment.getID())) {
							found = true;
							clickedItemPosition = counter;
						}
						counter++;
					}
					
					//Extract all the properties of that equipment
					clickedEquipment = tableModel.getEquipment(clickedItemPosition);
					
					//Set the fields to corresponding clicked item
	                textField_EquipmentType.setText(equipmentType);
					textField_ITEM_ID.setText(itemId);
					textBorrowStatus.setText(clickedEquipment.getStatus());
                }
            }
        };
    }
	
	/**
	 * Create the application.
	 */
	public EquipmentsTeachers() {
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 6, 223, 248);
		frame.getContentPane().add(scrollPane);
		
		/*
		 * Parent node
		 */
		DefaultMutableTreeNode equipmentTree=new DefaultMutableTreeNode("Equipment");  
		
		/*
	     *--------------------------------------------------------------------------------------------- 
	     */
		
		/*
		 * Child 1 
		 */
	    DefaultMutableTreeNode testTubes=new DefaultMutableTreeNode("Test Tubes");  
	    DefaultMutableTreeNode beakers=new DefaultMutableTreeNode("Beakers");  
	    DefaultMutableTreeNode funnels=new DefaultMutableTreeNode("Funnels");  
	    DefaultMutableTreeNode cylinder=new DefaultMutableTreeNode("Graduated Cylinders");  
	    DefaultMutableTreeNode thermometer=new DefaultMutableTreeNode("Thermometers");  
	    //Adding child nodes to below parent
	    equipmentTree.add(testTubes);  
	    equipmentTree.add(beakers);  
	    equipmentTree.add(funnels);  
	    equipmentTree.add(cylinder);  
	    equipmentTree.add(thermometer);  
	    
	    /*
	     *--------------------------------------------------------------------------------------------- 
	     */
	    
	    /*
	     * Child nodes 2
	     */
	    
	    /*
	     * Test Tubes
	     */
	    DefaultMutableTreeNode testTube1000=new DefaultMutableTreeNode("1000");  
	    DefaultMutableTreeNode testTube1001=new DefaultMutableTreeNode("1001");  
	    DefaultMutableTreeNode testTube1002=new DefaultMutableTreeNode("1002");  
	    DefaultMutableTreeNode testTube1003=new DefaultMutableTreeNode("1003");  
	    testTubes.add(testTube1000); testTubes.add(testTube1001); testTubes.add(testTube1002); testTubes.add(testTube1003);      
	    
	    /*
	     * Beakers
	     */
	    DefaultMutableTreeNode beaker2090=new DefaultMutableTreeNode("2090");  
	    DefaultMutableTreeNode beaker2091=new DefaultMutableTreeNode("2091");  
	    DefaultMutableTreeNode beaker2092=new DefaultMutableTreeNode("2092");  
	    DefaultMutableTreeNode beaker2093=new DefaultMutableTreeNode("2093"); 
	    beakers.add(beaker2090);beakers.add(beaker2091);beakers.add(beaker2092);beakers.add(beaker2093);
	    
	    /*
	     * Funnels
	     */
	    DefaultMutableTreeNode funnel3040=new DefaultMutableTreeNode("3040");  
	    DefaultMutableTreeNode funnel3041=new DefaultMutableTreeNode("3041");  
	    DefaultMutableTreeNode funnel3042=new DefaultMutableTreeNode("3042");  
	    DefaultMutableTreeNode funnel3043=new DefaultMutableTreeNode("3043"); 
	    funnels.add(funnel3040);funnels.add(funnel3041);funnels.add(funnel3042);funnels.add(funnel3043);
	    
	    /*
	     * Graduated Cylinders
	     */
	    DefaultMutableTreeNode cylinder4080=new DefaultMutableTreeNode("4080");
	    DefaultMutableTreeNode cylinder4081=new DefaultMutableTreeNode("4081");
	    DefaultMutableTreeNode cylinder4082=new DefaultMutableTreeNode("4082");
	    DefaultMutableTreeNode cylinder4083=new DefaultMutableTreeNode("4083");
	    cylinder.add(cylinder4080);cylinder.add(cylinder4081);cylinder.add(cylinder4082);cylinder.add(cylinder4083);
	    
	    /*
	     * Thermometers
	     */
	    DefaultMutableTreeNode therm5100=new DefaultMutableTreeNode("5100");
	    DefaultMutableTreeNode therm5101=new DefaultMutableTreeNode("5101");
	    DefaultMutableTreeNode therm5102=new DefaultMutableTreeNode("5102");
	    DefaultMutableTreeNode therm5103=new DefaultMutableTreeNode("5103");
	    thermometer.add(therm5100);thermometer.add(therm5101);thermometer.add(therm5102);thermometer.add(therm5103);
	    
	    /*
	     *--------------------------------------------------------------------------------------------- 
	     */
	    
	    JTree tree = new JTree(equipmentTree);
		scrollPane.setViewportView(tree);
		
		//Using listener
		tree.addTreeSelectionListener(createSelectionListener());
		
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close current window
				frame.dispose();
				//Take user back to Home Screen Screen
				UIMain.main(null);
			}
		});
		btnHome.setBounds(259, 237, 91, 29);
		frame.getContentPane().add(btnHome);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close current window
				frame.dispose();
				//Take user to Login Screen
				UserLoginScreen.main(null);
			}
		});
		btnLogOut.setBounds(353, 237, 91, 29);
		frame.getContentPane().add(btnLogOut);
		
		JLabel labelStatus = new JLabel("Item Status:");
		labelStatus.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		labelStatus.setBounds(264, 91, 86, 16);
		frame.getContentPane().add(labelStatus);
		
		//Box to display borrow status of clicked item
		textBorrowStatus = new JTextField();
		textBorrowStatus.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		textBorrowStatus.setColumns(10);
		textBorrowStatus.setBounds(325, 90, 111, 16);
		frame.getContentPane().add(textBorrowStatus);
		
		//Clear button
		JButton btnClear = new JButton("Un-select");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_EquipmentType.setText(null);
				textField_ITEM_ID.setText(null);
				textBorrowStatus.setText(null);
			}
		});
		btnClear.setBounds(274, 206, 148, 29);
		frame.getContentPane().add(btnClear);
		
		JLabel labelItemName = new JLabel("Equipment Type:");
		labelItemName.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		labelItemName.setBounds(264, 35, 86, 16);
		frame.getContentPane().add(labelItemName);
		
		//Box to display equipment name of clicked item
		textField_EquipmentType = new JTextField();
		textField_EquipmentType.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_EquipmentType.setColumns(10);
		textField_EquipmentType.setBounds(335, 34, 101, 16);
		frame.getContentPane().add(textField_EquipmentType);
		
		JLabel lblEquipmentId = new JLabel("Equipment ID:");
		lblEquipmentId.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		lblEquipmentId.setBounds(264, 63, 86, 16);
		frame.getContentPane().add(lblEquipmentId);
		
		//Box to display equipment ID of clicked item
		textField_ITEM_ID = new JTextField();
		textField_ITEM_ID.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		textField_ITEM_ID.setColumns(10);
		textField_ITEM_ID.setBounds(325, 63, 111, 16);
		frame.getContentPane().add(textField_ITEM_ID);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(259, 23, 185, 103);
		frame.getContentPane().add(panel);
		
		JLabel lblEquipment = new JLabel("Equipment Information:");
		lblEquipment.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 12));
		lblEquipment.setBounds(264, 6, 162, 16);
		frame.getContentPane().add(lblEquipment);
		
		/*
		 * BORROW BUTTON
		 */
		JButton btnBorrow = new JButton("BORROW");
		
		//The user that is currently borrowing items --> so we look for them in json
		currentUser = UserLoginScreen.loggedInUser;
		currentUserID = UserLoginScreen.loggedInUserID;
		
		//Listener function
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if one of the fields is empty, inform them operation cannot be performed
				if(textBorrowStatus.getText().equals("") || textField_EquipmentType.getText().equals("") || textField_ITEM_ID.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill in every field","Please fill in every field", JOptionPane.ERROR_MESSAGE);
				}
				else 
				{ 
					//If it is not available
					if(textBorrowStatus.getText().equals("borrowed")) {
						JOptionPane.showMessageDialog(null, "Item not available","Item not available", JOptionPane.ERROR_MESSAGE);
					}
					else {
						//
						//We access the clicked item
						//We delete it, and add it again, but with different HolderID, HolderName, and Status
						//
						
						//Get the current selected item's ID
						selectedEquipmentID = textField_ITEM_ID.getText();
						
						//Store the current selected item's Type
						selectedEquipmentType = textField_EquipmentType.getText();
						
						
						//Loading in JSON to check list of equipments and their properties
	                	tableModel = new EquipmentTableModel();
						tableModel.load();
						
						//Looping through the equipments until we find the right equipment with matching ID
						int tableSize = tableModel.getRowCount();
						int counter = 0;
						boolean found = false;
						while(found == false && counter < tableSize) {
							EquipmentClass equipment = tableModel.getEquipment(counter);
							//Check if 
							if(selectedEquipmentID.equals(equipment.getID())) {
								found = true;
								clickedItemPosition = counter;
							}
							counter++;
						}
						
						//Now we delete that equipment
						tableModel.removeRowAt(clickedItemPosition);
						
						//And add the equipment with same type, same ID, but new Holder Name, HolderID, and status
						String equipmentType = selectedEquipmentType;
						String equipmentID = selectedEquipmentID;
						String newHolderName = currentUser;
						String newHolderID = currentUserID;
						String newStatus = "borrowed";
						
						//Create a new equipment based on that new info
						EquipmentClass newEquipment = new EquipmentClass(equipmentType,equipmentID,newStatus,newHolderName,newHolderID);
						tableModel.addEquipment(newEquipment);
						
						//Save the changes
						tableModel.save();
						
						//Message Dialog Box
						JOptionPane.showMessageDialog(null, "Item Borrowed Successfully!","Item Borrowed Successfully!", JOptionPane.INFORMATION_MESSAGE);
						
						//close the current window
						frame.dispose();
						
						//Go back to menu selection page for teachers
						UIMain.main(null);
					}
				}
			}
		});
		btnBorrow.setBounds(264, 150, 162, 44);
		frame.getContentPane().add(btnBorrow);
	}
}
