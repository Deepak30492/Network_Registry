package com.uhi.network_registry_api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uhi.network_registry_api.models.Subscriber;
import com.uhi.network_registry_api.services.SubscriberService;

@RestController
@RequestMapping("/api")
public class SubscriberController {

	@Autowired
	SubscriberService subscriberService;

	@PostMapping("/lookup")
	public ResponseEntity<String> lookupSubscribers(@RequestBody Map<String, Object> subscriberMap)
			throws JsonProcessingException {
		// {country=IND, city=std:080, domain=nic2004:85110, type=BPP,
		// status=SUBSCRIBED}
		Subscriber subscriber = null;

		String country = (String) subscriberMap.get("country");
		String city = (String) subscriberMap.get("city");
		String domain = (String) subscriberMap.get("domain");
		String type = (String) subscriberMap.get("type");
		String status = (String) subscriberMap.get("status");

		if ("BAP".equalsIgnoreCase(type)) {
			String subscriber_id = (String) subscriberMap.get("subscriber_id");
			subscriber = new Subscriber(subscriber_id, country, city, domain, type, status);
		} else {
			subscriber = new Subscriber(country, city, domain, type, status);
		}

		List<Subscriber> result = subscriberService.lookup(subscriber);
		String json = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(result);
		return new ResponseEntity<>(json, HttpStatus.OK);
	}

	@PostMapping("/subscribe")
	public String subscribeParticipant(@RequestBody Map<String, String> userMap) {
		subscriberService.addSubscriber(userMap.get("subscriber_id"), userMap.get("country"), userMap.get("city"),
				userMap.get("domain"), userMap.get("unique_key_id"), userMap.get("pub_key_id"),
				userMap.get("signing_public_key"), userMap.get("encr_public_key"), userMap.get("status"),
				userMap.get("radius"), userMap.get("sub_type"), userMap.get("url"));
		return userMap.toString();
	}
}
