package bdd.pguerra.utilities;

import java.util.ArrayList;
import java.util.List;

import bdd.pguerra.pojo.AddPlace;
import bdd.pguerra.pojo.AddPlaceLocation;

public class TestDataBuild {
	public AddPlace addPlacePayload(String name, String language, String address) {
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		
		AddPlaceLocation l = new AddPlaceLocation();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		AddPlace p = new AddPlace();
		
		p.setLocation(l);
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		p.setTypes(myList);
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		
		return p;
	}
	
	public String deletePlacePayload(String placeID) {
		return "{" + 
				"\"place_id\": \"" + placeID + "\"" + 
				"}";
	}
}
