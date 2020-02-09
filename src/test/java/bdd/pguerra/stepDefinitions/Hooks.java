package bdd.pguerra.stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefinitionPlaceValidation steps = new StepDefinitionPlaceValidation();
		
		if(StepDefinitionPlaceValidation.place_id == null) {
			steps.add_place_payload("Shetty", "Franch", "Asia");
			steps.user_calls_with_http_request("AddPlaceAPI", "POST");
			steps.verify_created_maps_to_using("place_id", "Shetty", "GetPlaceAPI");
		}
	}
}
