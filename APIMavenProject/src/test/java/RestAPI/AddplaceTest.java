package RestAPI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import googleAPI.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


import static io.restassured.RestAssured.given;

public class AddplaceTest {
	
	
    Properties prop = new Properties();
    private static Logger log = LogManager.getLogger(AddplaceTest.class.getName());
	
    @BeforeTest
	public void readproperties() throws IOException
	{
		
		FileInputStream fis  = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		
		prop.load(fis);
	}
	
	@Test
	public void addplace_postrequest()
	{
		log.info("Starting test", prop.getProperty("HOST"));
		RestAssured.baseURI = prop.getProperty("HOST");
		
		
		given().
		      queryParam("key",prop.getProperty("POSTKEY")).
		      body(payload.postpayload()).log().all().
		      when().
		             post(resources.postresource()).
		      then()
		            .assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		            body("status",equalTo("OK")).log().all();
		
		// Create a place =response (place id)

		// delete Place = (Request - Place id)


		      
	}

}
