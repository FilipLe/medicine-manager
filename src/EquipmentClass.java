import java.math.BigDecimal;

import com.github.cliftonlabs.json_simple.JsonObject;

/**
 * TEST FILE for the Medicine Class
 * 
 * @author nguyenle
 *
 */
public class EquipmentClass {
	private String type;
	private String ID;
	private String status;
	private String holderName;
	private String holderID;
	
	//Constructor for Equipment
	EquipmentClass(String type, String ID, String status, String holderName, String holderID){
		this.type = type;
		this.ID = ID;
		this.status = status;
		this.holderName = holderName;
		this.holderID = holderID;
	}
	
	/*
	 * Method for user to call out info
	 */
	public String getType() {
		return type;
	}
	
	public String getID() {
		return ID;
	}
	
	public String getStatus() {
		return status;
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
		properties.put("status", status);
		properties.put("holderName", holderName);
		properties.put("holderID", holderID);
		
		/*
		 * Format we want to save it in:
		 * {
		 * 	1014:{"name":"test tube","status":"unavailable","holder name":"Joe","holderID":"1292"},
		 * 	2032:{"name":"beaker","status":"available","holder name":"null","holderID":"null"}
		 * }
		 */
		return properties;
	}
	
	//This is going to take a Json Object and return an equipment with all the data stored in that object
	public static EquipmentClass fromJsonObject(String ID,JsonObject properties) {
		//We use EquipmentID as Key because it is unique
		String type = (String)properties.get("type");
		String status = (String)properties.get("status");
		String holderName = (String)properties.get("holderName");
		String holderID = (String)properties.get("holderID");
		return new EquipmentClass(type,ID,status,holderName,holderID);
	}
}
