import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

public class EquipmentTableModel extends AbstractTableModel{
	private EquipmentClass[] presetMedications = new EquipmentClass[] 
	{

	};
	
	//Creating arrayList to store the users
	private ArrayList<EquipmentClass> medicationList = new ArrayList<>();
	
	//Add default to array
	EquipmentTableModel()
	{
		medicationList.addAll(Arrays.asList(presetMedications));
	}
	

	public EquipmentClass getMedication(int rowIndex) {
		return medicationList.get(rowIndex);
	}
	
	
	public void addMedication(EquipmentClass newEquipment) {
		//Adding new user into the table
		medicationList.add(newEquipment);
		
		//updates table
		fireTableDataChanged();
	}

	public void removeRowAt(int row) {
		medicationList.remove(row);
	    //fireTableDataChanged();
	    fireTableRowsDeleted(row - 1, medicationList.size() - 1);
	}
	

	/*
	 * Column Headings
	 */
	public String getColumnName(int columnIndex) 
	{
		if(columnIndex == 0) 
		{
			return "Medicine Type";
		}
		else if(columnIndex == 1)
		{
			return "Medicine Name";
		}
		else if(columnIndex == 2)
		{
			return "Quantity";
		}
		else if(columnIndex == 3)
		{
			return "Holder Name";
		}
		else if(columnIndex == 4)
		{
			return "Holder ID";
		}
		else
		{
			return null;
		}
	}
	
	
	public int getRowCount() {
		// Amount of users
		return medicationList.size();
	}


	public int getColumnCount() {
		// 2 columns: Name + UserID
		return 5;
	}


	public Object getValueAt(int rowIndex, int columnIndex) {
		EquipmentClass medication = medicationList.get(rowIndex);
		/*
		 * DISPLAYING INPUT ON TABLE
		 */
		if(columnIndex == 0) 
		{
			return medication.getType();
		}
		else if(columnIndex == 1)
		{
			return medication.getName();
		}
		else if(columnIndex == 2)
		{
			return medication.getQuantity();
		}
		else if(columnIndex == 3)
		{
			return medication.getHolderName();
		}
		else if(columnIndex == 4)
		{
			return medication.getHolderID();
		}
		else
		{
			return null;
		}
	}
	
	
	
	//Default path to save the data — home directory
	private Path getDefaultPath() {
		String home = System.getProperty("user.home");
		return Paths.get(home).resolve("medList.json");
	}
	
	
	//Saving as Json Data without path provided - in user's home directory
	public void save() {
		save(getDefaultPath());
	}
	
	
	
	//Saving as Json Data with path provided
	public void save(Path path) {
		//Converting all of the Users in the table model into JSON Objects
		JsonObject ja = new JsonObject();
		
		//For each user in the list of users, we will add them into the json array
		for(EquipmentClass medication : medicationList) {
			ja.put(medication.getName(), medication.toJsonObject());
		}
		
		//Convert Json array to json text
		String jsontext = Jsoner.serialize(ja);
		
		//Writing that json text to the file path
		try {
			Files.write(path, jsontext.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	//Load method from the default path
	public void load() {
		load(getDefaultPath());
	}
	
	
	
	//Method to load back the data when we run the program
	public void load(Path path) {
		
		//Declaring json variables
		String jsonText = null;
		JsonObject rootObject = null;
		
		//Read in the text from the file
		try {
			jsonText = new String(Files.readAllBytes(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
		
		//Convert the text into Json Objects
		try {
			rootObject = (JsonObject)Jsoner.deserialize(jsonText);
		} catch (JsonException e) {
			throw new RuntimeException(e);
		}
		
		for(Object key : rootObject.keySet()) {
			String Name = key.toString();
			//Obtaining JSON Object using unique name, which is the key of the medication object
			JsonObject jo = (JsonObject)rootObject.get(Name);
			
			//Getting the JSON object of the medication with matching name
			EquipmentClass medication = EquipmentClass.fromJsonObject(Name, jo);
			
			//add that user to the list of users
			medicationList.add(medication);
		}
	}
}
