import java.math.BigDecimal;

import com.github.cliftonlabs.json_simple.JsonObject;

/**
 * TEST FILE for the Medicine Class
 * 
 * @author nguyenle
 *
 */
public class EquipmentClass {
	private String type; //equipment type --> medicine type
	private String name; //equipment ID --> medicine name
	private String quantity; //availability status --> Qty — quantity in stock (INTEGER)
	private String holderName;
	private String holderID;
	
	//Constructor for Equipment
	EquipmentClass(String type, String name, String quantity, String holderName, String holderID){
		this.type = type;
		this.name = name;
		this.quantity = quantity;
		this.holderName = holderName;
		this.holderID = holderID;
	}
	
	/*
	 * Method for user to call out info
	 */
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public String getHolderName(){
		return holderName;
	}
	
	public String getHolderID() {
		return holderID;
	}
	
	
	/*
	 * Converting to json
	 */
	public JsonObject toJsonObject() {
		//<key>:<value>
		JsonObject properties = new JsonObject();
		properties.put("type", type);
		properties.put("quantity", quantity);
		properties.put("holderName", holderName);
		properties.put("holderID", holderID);
		
		/*
		 * Format we want to save it in:
		 * {
		 * 	aspirin:{"type":"pain killers","quantity":"25","holder name":"Joe","holderID":"1292"},
		 * 	fentanyl:{"type":"pain killers","quantity":"1","holder name":"null","holderID":"null"}
		 * }
		 */
		return properties;
	}
	
	//This is going to take a Json Object and return an equipment with all the data stored in that object
	public static EquipmentClass fromJsonObject(String name,JsonObject properties) {
		
		//We use Medicine Name as Key because it is unique (e.g. aspirin, ibuprofen, etc.)
		String type = (String)properties.get("type");
		String quantity = (String)properties.get("quantity");
		String holderName = (String)properties.get("holderName");
		String holderID = (String)properties.get("holderID");
		return new EquipmentClass(type,name,quantity,holderName,holderID);
	}
}
