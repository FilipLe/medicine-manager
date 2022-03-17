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
	private EquipmentClass[] presetEquipment = new EquipmentClass[] 
	{

	};
	
	//Creating arrayList to store the users
	private ArrayList<EquipmentClass> equipmentList = new ArrayList<>();
	
	//Add default to array
	EquipmentTableModel()
	{
		equipmentList.addAll(Arrays.asList(presetEquipment));
	}
	

	public EquipmentClass getEquipment(int rowIndex) {
		return equipmentList.get(rowIndex);
	}
	
	
	public void addEquipment(EquipmentClass newEquipment) {
		//Adding new user into the table
		equipmentList.add(newEquipment);
		
		//updates table
		fireTableDataChanged();
	}

	public void removeRowAt(int row) {
		equipmentList.remove(row);
	    //fireTableDataChanged();
	    fireTableRowsDeleted(row - 1, equipmentList.size() - 1);
	}
	

	/*
	 * Column Headings (Name, UserID, Password
	 */
	public String getColumnName(int columnIndex) 
	{
		if(columnIndex == 0) 
		{
			return "Equipment Type";
		}
		else if(columnIndex == 1)
		{
			return "Equipment ID";
		}
		else if(columnIndex == 2)
		{
			return "Status";
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
		return equipmentList.size();
	}


	public int getColumnCount() {
		// 2 columns: Name + UserID
		return 5;
	}


	public Object getValueAt(int rowIndex, int columnIndex) {
		EquipmentClass equipment = equipmentList.get(rowIndex);
		/*
		 * DISPLAYING INPUT ON TABLE
		 */
		if(columnIndex == 0) 
		{
			return equipment.getType();
		}
		else if(columnIndex == 1)
		{
			return equipment.getID();
		}
		else if(columnIndex == 2)
		{
			return equipment.getStatus();
		}
		else if(columnIndex == 3)
		{
			return equipment.getHolderName();
		}
		else if(columnIndex == 4)
		{
			return equipment.getHolderID();
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
		for(EquipmentClass equipment : equipmentList) {
			ja.put(equipment.getID(), equipment.toJsonObject());
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
			String Id = key.toString();
			//Obtaining JSON Object using ID, which is the key of the equipment object
			JsonObject jo = (JsonObject)rootObject.get(Id);
			
			//Getting the JSON object of the equipment with matching ID
			EquipmentClass equipment = EquipmentClass.fromJsonObject(Id, jo);
			
			//add that user to the list of users
			equipmentList.add(equipment);
		}
	}
}
