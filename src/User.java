import java.math.BigDecimal;

import com.github.cliftonlabs.json_simple.JsonObject;

public class User {
	private String name;
	private String UserID;
	
	User(String name, String UserID)
	{
		this.name = name;
		this.UserID = UserID;
	}
	
	/*
	 * This is for printing out details after creating account
	 */
	
	//Allowing program to output user's name
	public String getName() 
	{
		return name;
	}
	
	//Output UserID
	public String getUserID()
	{
		return UserID;
	}
	
	
	//Converting to JSONObj()
	public JsonObject toJsonObject() {
		JsonObject jo = new JsonObject();
		
		//JSON format: <key>:<value> --> UserID is unique ==> Make it a key
		jo.put("name", name);
		
		/*
		 * Format of data:
		 * {
		 *  "0": { "name" : "Admin" },
		 *  "8732": { "name" : "Filip" }
		 *	}
		 */
		
		//return the JSON Object created
		return jo;
	}
	
	//This is going to take a Json Object and return a user with all the data stored in that object
	public static User fromJsonObject(String userID, JsonObject jo) {
		//Instead of strings name and UserID, we need it to get key and values pair
		String name = (String)jo.get("name");
		return new User(name,userID);
	}
}

