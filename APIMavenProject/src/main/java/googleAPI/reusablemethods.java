package googleAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;



public class reusablemethods {

	
	public static XmlPath rawToXML(Response r)
	{
		String response = r.asString();
		
		XmlPath xmldata = new XmlPath(response);
		return xmldata;
	}
	
	
	public static JsonPath rawToJson(Response r)
	{
		String response =r.asString();
		JsonPath jsondata = new JsonPath(response);
		return jsondata;
	}
}
