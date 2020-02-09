package bdd.pguerra.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification reqSpecBuilder;
	
	public RequestSpecification getRequestSpecification() throws IOException {
		if(reqSpecBuilder == null ) {
			PrintStream log = new PrintStream(new FileOutputStream("loggin.txt"));
			
			reqSpecBuilder = new RequestSpecBuilder()
					//.log(io.restassured.filter.log.LogDetail.ALL)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON)
					.build();
		}
		return reqSpecBuilder;
	}
	
	public static String getGlobalValue(String key) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/java/bdd/pguerra/utilities/global.properties"));
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		JsonPath jp = new JsonPath(response.asString());
		return jp.get(key).toString();
	}
}
