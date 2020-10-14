package RestAPI;

import googleAPI.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class DeleteplaceTest {
	

	Properties prop = new Properties();
	  private static Logger log = LogManager.getLogger(DeleteplaceTest.class.getName());
	
    @BeforeTest
	public void readproperties() throws IOException
	{
		
		FileInputStream fis  = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		
		prop.load(fis);
	}
	
	
	@Test
	public void deleteplace_deleteresquest()
	{
		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		log.info("Starting test", DeleteplaceTest.class.getName());
		
		Response result = given().
		      queryParam("key", prop.getProperty("POSTKEY")).
		      body(payload.postpayload()).log().all().
           when().
                 post(resources.postresource()).
           then().
                 assertThat().statusCode(200).contentType(ContentType.JSON).log().all().
           extract().response();
		
		log.info("added place ", DeleteplaceTest.class.getName());
		//take the raw response and convert it to string
		
		JsonPath js =reusablemethods.rawToJson(result);
		String placeid= js.get("place_id");
		log.info(placeid);
		
		
		//Deleting the place that has been added by using place id
		
		given().
		queryParam("key",prop.getProperty("POSTKEY")).
		body(payload.deleteplaceid(placeid)).
		when().
		post(resources.deleteresource()).
		then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON);
		
		log.info("deleted place ", DeleteplaceTest.class.getName());
	}

}
