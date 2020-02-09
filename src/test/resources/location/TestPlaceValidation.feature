Feature: Validating Place API's

@addPlace
Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI
	Given add place payload <name> <language> <address>
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify "place_id" created maps to <name> using "GetPlaceAPI"
	
	Examples:
	|   name    | language  |       address        |
	| "AAhouse" | "English" | "World Cross Center" |
	| "BBhouse" | "Spanish" | "Sea Cross Center"   |
	

@deletePlace	
Scenario: Verify if delet place functionality is working
	Given DeletePlace payload
	When user calls "DeletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"