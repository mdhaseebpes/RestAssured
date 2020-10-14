package RestAPI;

import googleAPI.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import java.io.FileInputStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class adddeleteplacexmlTest {
	
	Properties prop = new Properties();
	private static Logger log = LogManager.getLogger(adddeleteplacexmlTest.class.getName());
	
	@BeforeTest
	public void readprop() throws Exception
	{
		FileInputStream fis  = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		
		prop.load(fis);
		
	}
	
	
	@Test
	public void xmladddelete() throws Exception
	
	{
		log.info("Starting test"+prop.getProperty("HOST") );
		String postdata = GenerateStringFromResource(System.getProperty("user.dir")+"\\resource\\xmlbody.xml");
		RestAssured.baseURI=prop.getProperty("HOST");
		
		
		Response result = given().
		       queryParam("key", prop.getProperty("POSTKEY")).
		       body(postdata).log().all().
		 when().
		       post(resources.postresourcexml()).
		  then().
		         assertThat().statusCode(200).contentType(ContentType.XML).log().all().
		  extract().response();
		
		
		XmlPath xml =reusablemethods.rawToXML(result);
		String placeidxml =xml.get("response.place_id");
		log.info(placeidxml);
		       
	}

	
	public static String GenerateStringFromResource(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
