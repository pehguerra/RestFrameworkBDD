package bdd.pguerra.stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import bdd.pguerra.utilities.APIResources;
import bdd.pguerra.utilities.TestDataBuild;
import bdd.pguerra.utilities.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinitionPlaceValidation extends Utils {
	RequestSpecification reqSpecBuilder;
	ResponseSpecification resSpecBuilder;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		reqSpecBuilder = given()
				.spec(getRequestSpecification())
				.body(data.addPlacePayload(name, language, address));
	}
	
	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
		reqSpecBuilder = given()
				.spec(getRequestSpecification())
				.body(data.deletePlacePayload(place_id));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		resSpecBuilder = new ResponseSpecBuilder()
				//.log(io.restassured.filter.log.LogDetail.ALL)
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		
		if(method.equalsIgnoreCase("POST")) 
			response = given()
					.spec(reqSpecBuilder)
				.when()
					.post(APIResources.valueOf(resource).getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = given()
					.spec(reqSpecBuilder)
				.when()
					.get(APIResources.valueOf(resource).getResource());
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(int HTTPStatusCode) {
	    assertEquals(HTTPStatusCode, response.getStatusCode());
	}


	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expecetedValue) {
	    assertEquals(expecetedValue, getJsonPath(response, key));
	    
	}
	
	@Then("verify {string} created maps to {string} using {string}")
	public void verify_created_maps_to_using(String key, String name, String resource) throws IOException {
		place_id = getJsonPath(response, key);
		
		reqSpecBuilder = given()
				.spec(getRequestSpecification())
				.queryParam(key, place_id);
		user_calls_with_http_request(resource, "GET");
		assertEquals(name, getJsonPath(response, "name"));
	}
}
