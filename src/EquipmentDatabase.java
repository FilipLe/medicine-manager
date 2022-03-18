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

public class EquipmentDatabase {

	/*
	 * MEDICATION DATABASE FOR USERS
	 */
		
	private JFrame frame;
	private JTextField txtHolderName;
	private JTextField textHolderID;
	private JTextField textQuantity;
	private JTextField textField_MedicineType;
	private JTextField textField_ITEM_NAME;
	private EquipmentTableModel tableModel;
	private EquipmentClass clickedMedication;
	private int positionOfMedication;
	private String selectedMedicineName;
	private String selectedMedicineType;
	private String selectedMedicineQuantity;
	private int clickedItemPosition;
	private int currentQuantity;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EquipmentDatabase window = new EquipmentDatabase();
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
                
                
                //Accessing the outer most Child node ==> The Medication name
                String itemName = path.getPathComponent(pathCount-1).toString();
                
                //Accessing the second outer most child node ==> medicine type
                String medicineType = path.getPathComponent(pathCount-2).toString();
					
            	//Loading in JSON to check list of medications and their properties
            	tableModel = new EquipmentTableModel();
				tableModel.load();
				
				//Looping through the medications until we find the right one
				int tableSize = tableModel.getRowCount();
				int counter = 0;
				boolean found = false;
				while(found == false && counter < tableSize) {
					EquipmentClass medication = tableModel.getMedication(counter);
					if(itemName.equals(medication.getName())) {
						found = true;
						positionOfMedication = counter;
					}
					counter++;
				}
					
				//Extract all the properties of that medication
				clickedMedication = tableModel.getMedication(positionOfMedication);
				
				//Set the fields to corresponding clicked item
                textField_MedicineType.setText(medicineType);
				textField_ITEM_NAME.setText(itemName);
				txtHolderName.setText(clickedMedication.getHolderName());
				textHolderID.setText(clickedMedication.getHolderID());
				textQuantity.setText(clickedMedication.getQuantity());		
            }
        };
    }
	
	/**
	 * Create the application.
	 */
	public EquipmentDatabase() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Medicine Manager");
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 6, 223, 248);
		frame.getContentPane().add(scrollPane);
		
		/*
		 * Parent node
		 */
		DefaultMutableTreeNode medicationTree=new DefaultMutableTreeNode("Medicine");  
		
		/*
	     *--------------------------------------------------------------------------------------------- 
	     */
		
		/*
		 * Child 1 
		 */
	    DefaultMutableTreeNode painKillers=new DefaultMutableTreeNode("Pain Killers");//test tubes --> pain killers
	    DefaultMutableTreeNode diabetes=new DefaultMutableTreeNode("Diabetes"); //beakers --> diabetes 
	    DefaultMutableTreeNode fever=new DefaultMutableTreeNode("Fever Reducer");  //fever --> fever reducer
	    DefaultMutableTreeNode stomach=new DefaultMutableTreeNode("Stomach Pain");  //graduated stomachs --> stomach pain
	    DefaultMutableTreeNode cold=new DefaultMutableTreeNode("Common Cold");
	    DefaultMutableTreeNode supplements=new DefaultMutableTreeNode("Supplements");
	    DefaultMutableTreeNode skin=new DefaultMutableTreeNode("Skin-related");
	    DefaultMutableTreeNode prescription=new DefaultMutableTreeNode("Prescription Drugs");  //thermometers --> special prescription drugs
	    //Adding child nodes to below parent
	    medicationTree.add(painKillers);  
	    medicationTree.add(diabetes);  
	    medicationTree.add(fever);  
	    medicationTree.add(stomach);
	    medicationTree.add(cold);
	    medicationTree.add(supplements);
	    medicationTree.add(skin);
	    medicationTree.add(prescription);  
	    
	    /*
	     *--------------------------------------------------------------------------------------------- 
	     */
	    
	    /*
	     * Child nodes 2
	     */
	    	    
	    /*
	     * Test Tubes --> Pain Killers
	     */
	    DefaultMutableTreeNode aspirin=new DefaultMutableTreeNode("Aspirin");    
	    DefaultMutableTreeNode fentanyl=new DefaultMutableTreeNode("Fentanyl");  
	    DefaultMutableTreeNode nurofen=new DefaultMutableTreeNode("Nurofen");  
	    painKillers.add(aspirin); painKillers.add(fentanyl); painKillers.add(nurofen);      
	    
	    /*
	     * Beakers --> Diabetes
	     */
	    DefaultMutableTreeNode glucophage=new DefaultMutableTreeNode("Glucophage500");  
	    DefaultMutableTreeNode trajenta=new DefaultMutableTreeNode("Trajenta");  
	    DefaultMutableTreeNode diben=new DefaultMutableTreeNode("Diben"); 
	    diabetes.add(glucophage);diabetes.add(trajenta);diabetes.add(diben);
	    
	    /*
	     * Funnels --> Fever Reducer
	     */
	    DefaultMutableTreeNode paracetamol=new DefaultMutableTreeNode("Paracetamol");  
	    DefaultMutableTreeNode ibuprofenFever=new DefaultMutableTreeNode("Ibuprofen");  
	    DefaultMutableTreeNode naproxen=new DefaultMutableTreeNode("Naproxen");  
	    fever.add(paracetamol);fever.add(ibuprofenFever);fever.add(naproxen);
	    
	    /*
	     * Graduated Cylinders --> Stomach Pain
	     */
	    DefaultMutableTreeNode loperamid=new DefaultMutableTreeNode("LoperamidSTADA");
	    DefaultMutableTreeNode enterogermina=new DefaultMutableTreeNode("Enterogermina");
	    DefaultMutableTreeNode smecta=new DefaultMutableTreeNode("Smecta");
	    stomach.add(loperamid);stomach.add(enterogermina);stomach.add(smecta);
	    
	    /*
	     * Common Cold
	     */
	    DefaultMutableTreeNode colgrip=new DefaultMutableTreeNode("Colgrip");
	    DefaultMutableTreeNode strepsil=new DefaultMutableTreeNode("Strepsil");
	    DefaultMutableTreeNode fervex=new DefaultMutableTreeNode("Fervex");
	    cold.add(colgrip);cold.add(strepsil);cold.add(fervex);
	    
	    /*
	     * Supplements 
	     */
	    DefaultMutableTreeNode rutinoscrobin=new DefaultMutableTreeNode("Rutinoscrobin");
	    DefaultMutableTreeNode sema=new DefaultMutableTreeNode("SEMALAB");
	    DefaultMutableTreeNode biovital=new DefaultMutableTreeNode("Biovital");
	    DefaultMutableTreeNode magnesium=new DefaultMutableTreeNode("MagnesiumCitrate");
	    supplements.add(rutinoscrobin);supplements.add(sema);supplements.add(biovital);supplements.add(magnesium);
	    
	    /*
	     * Dermatological
	     */
	    DefaultMutableTreeNode ovixan=new DefaultMutableTreeNode("Ovixan");
	    DefaultMutableTreeNode dexapolcort=new DefaultMutableTreeNode("Dexapolcort");
	    DefaultMutableTreeNode dexyane=new DefaultMutableTreeNode("Dexyane");
	    DefaultMutableTreeNode pimafucort=new DefaultMutableTreeNode("Pimafucort");
	    skin.add(ovixan);skin.add(dexapolcort);skin.add(dexyane);skin.add(pimafucort);
	    
	    /*
	     * Thermometers --> Special Prescription Drugs
	     */
	    DefaultMutableTreeNode symex=new DefaultMutableTreeNode("Symex");
	    DefaultMutableTreeNode penicillin=new DefaultMutableTreeNode("Penicillin");
	    DefaultMutableTreeNode nitrofurantoin=new DefaultMutableTreeNode("Nitrofurantoin");
	    prescription.add(symex);prescription.add(penicillin);prescription.add(nitrofurantoin);
	    
	    /*
	     *--------------------------------------------------------------------------------------------- 
	     */
	    
	    JTree tree = new JTree(medicationTree);
		scrollPane.setViewportView(tree);
		
		//Using listener
		tree.addTreeSelectionListener(createSelectionListener());
		
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close current window
				frame.dispose();
				//Take user back to Index Screen
				AdminIndex.main(null);
			}
		});
		btnHome.setBounds(353, 209, 91, 29);
		frame.getContentPane().add(btnHome);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close current window
				frame.dispose();
				//Take user to Login Screen
				UIMain.main(null);
			}
		});
		btnLogOut.setBounds(353, 237, 91, 29);
		frame.getContentPane().add(btnLogOut);
		
		/*
	     *--------------------------------------------------------------------------------------------- 
	     */
		
		/*
		 * Displaying info of the chosen item
		 */
		
		JLabel labelHolderName = new JLabel("Holder Name:");
		labelHolderName.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		labelHolderName.setBounds(269, 61, 86, 16);
		frame.getContentPane().add(labelHolderName);
		
		JLabel labelHolderID = new JLabel("Holder ID:");
		labelHolderID.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		labelHolderID.setBounds(269, 89, 86, 16);
		frame.getContentPane().add(labelHolderID);
		
		JLabel labelQuantity = new JLabel("Quantity:");
		labelQuantity.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		labelQuantity.setBounds(269, 117, 86, 16);
		frame.getContentPane().add(labelQuantity);
		
		//Box to display user name of clicked item
		txtHolderName = new JTextField();
		txtHolderName.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtHolderName.setBounds(325, 60, 101, 16);
		frame.getContentPane().add(txtHolderName);
		txtHolderName.setColumns(10);
		
		//Box to display User ID  of clicked item
		textHolderID = new JTextField();
		textHolderID.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		textHolderID.setColumns(10);
		textHolderID.setBounds(325, 88, 101, 16);
		frame.getContentPane().add(textHolderID);
		
		//Box to display borrow status of clicked item
		textQuantity = new JTextField();
		textQuantity.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		textQuantity.setColumns(10);
		textQuantity.setBounds(325, 116, 101, 16);
		frame.getContentPane().add(textQuantity);
		
		//Clear button
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MedicineType.setText(null);
				textField_ITEM_NAME.setText(null);
				txtHolderName.setText(null);
				textHolderID.setText(null);
				textQuantity.setText(null);
			}
		});
		btnClear.setBounds(353, 181, 91, 29);
		frame.getContentPane().add(btnClear);
		
		JLabel lblType = new JLabel("Medicine Type:");
		lblType.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		lblType.setBounds(269, 11, 86, 16);
		frame.getContentPane().add(lblType);
		
		//Box to display medication type of clicked item
		textField_MedicineType = new JTextField();
		textField_MedicineType.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		textField_MedicineType.setColumns(10);
		textField_MedicineType.setBounds(335, 10, 91, 16);
		frame.getContentPane().add(textField_MedicineType);
		
		JLabel lblMedicationName = new JLabel("Medicine Name:");
		lblMedicationName.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		lblMedicationName.setBounds(269, 33, 86, 16);
		frame.getContentPane().add(lblMedicationName);
		
		//Box to display medication name of clicked item
		textField_ITEM_NAME = new JTextField();
		textField_ITEM_NAME.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		textField_ITEM_NAME.setColumns(10);
		textField_ITEM_NAME.setBounds(335, 33, 91, 16);
		frame.getContentPane().add(textField_ITEM_NAME);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(259, 6, 173, 164);
		frame.getContentPane().add(panel);
		
		/*
		 * RESTOCK button
		 */
		JButton btnRestockMedication = new JButton("ADD");
		btnRestockMedication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * When this is clicked ==> Basically admin restocking
				 * holder ID and holder Name is set to default "Admin" and "0"
				 * quantity --> +20 meds
				 */
				
				//If no medication is selected --> error
				if(textField_MedicineType.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please select an item.","Please select an item.", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
					//Get the current selected medicine's name
					selectedMedicineName = textField_ITEM_NAME.getText();
						
					//Get the current selected medicine Type
					selectedMedicineType = textField_MedicineType.getText();
					
					//Get the current quantity of that medicine 
					selectedMedicineQuantity = textQuantity.getText();
					
					
						
					//Loading in JSON to check list of medications and their properties
	                tableModel = new EquipmentTableModel();
					tableModel.load();
						
					//Looping through the medications until we find the right medication with matching ID
					int tableSize = tableModel.getRowCount();
					int counter = 0;
					boolean found = false;
					while(found == false && counter < tableSize) {
						EquipmentClass medication = tableModel.getMedication(counter);
						//Check if 
						if(selectedMedicineName.equals(medication.getName())) {
							found = true;
							clickedItemPosition = counter;
						}
						counter++;
					}
						
					//Now we delete that medication
					tableModel.removeRowAt(clickedItemPosition);
						
					//And add the medication with same type, same ID, but Admin Holder Name and HolderID and different quantity
					String medicineType = selectedMedicineType;
					String medicineName = selectedMedicineName;
					String newHolderName = "Admin";
					String newHolderID = "0";
						
					if (isInteger(selectedMedicineQuantity) == true)
					{
						//convert to integer
						currentQuantity = Integer.parseInt(selectedMedicineQuantity);
						//Every restock, 20 items is added
						currentQuantity += 20;
					}
					//Change that quantity in the system
					String newQuantity = Integer.toString(currentQuantity);
						
					//Create a new medication based on that new info
					EquipmentClass newMedicine = new EquipmentClass(medicineType,medicineName,newQuantity,newHolderName,newHolderID);
					tableModel.addMedication(newMedicine);
						
					//Save the changes
					tableModel.save();
						
					//Message Dialog Box
					JOptionPane.showMessageDialog(null, "Item Restocked Successfully!","Item Restocked Successfully!", JOptionPane.INFORMATION_MESSAGE);
						
					//close the current window
					frame.dispose();
						
					//Go back to menu selection page for teachers
					UIMain.main(null);
					
				}
				
			}
		});
		
		btnRestockMedication.setBounds(254, 194, 101, 60);
		frame.getContentPane().add(btnRestockMedication);
	}
}
