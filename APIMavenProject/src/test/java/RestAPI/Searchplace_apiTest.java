package RestAPI;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.*;

import googleAPI.*;


/**
 * @author Mohammedk
 *
 */
public class Searchplace_apiTest {
	
	private static Logger log = LogManager.getLogger(Searchplace_apiTest.class.getName());
	Properties prop = new Properties();
	
	@BeforeTest
	public void Readprop() throws Exception
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		prop.load(fis);
	}

	@Test
	public void place(){
		// TODO Auto-generated method stub
		log.info("Search place  " + prop.getProperty("GOOGLEHOST"));

		RestAssured.baseURI=prop.getProperty("GOOGLEHOST");

		Response res = given().
		         param("location", "-33.8670522,151.1957362").param("radius","1500").
				 param("key", "AIzaSyAc0zcWKl7vo9-OmyK8WgdqsJjEBpjgqkA").log().all().
		when()
				.get(resources.getresources()).
		then().
		         assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().
				header("Server", "scaffolding on HTTPServer2").log().body().
		extract().response();
		
		JsonPath js  =reusablemethods.rawToJson(res);
       int count = js.get("results.size()");
		
		for(int i=0;i<count;i++)
		{
			String placename = js.get("results["+i+"].name");
			log.info(placename);
		}
		log.info(count);
	}

}
