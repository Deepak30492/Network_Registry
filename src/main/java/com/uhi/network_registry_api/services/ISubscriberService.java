package com.uhi.network_registry_api.services;

import java.util.List;

import com.uhi.network_registry_api.exceptions.NRAuthException;
import com.uhi.network_registry_api.models.Subscriber;

public interface ISubscriberService {

	Subscriber validateSubscriber(String subscriber_id, String unique_key_id, String pub_key_id) throws NRAuthException;

	Subscriber addSubscriber(String subscriber_id, String country, String city, String domain, String unique_key_id,
			String pub_key_id, String signing_public_key, String encr_public_key,
			String status, String radius, String sub_type, String url) throws NRAuthException;

	// TODO: Update the required parameters
	Subscriber updateSubscriber(Integer participant_id, String city, String encr_public_key, String pub_key_id,
			String signing_public_key, String subscriber_id, String unique_key_id, String url) throws NRAuthException;

	Subscriber findSubscriber(String subscriber_id);

	List<Subscriber> lookup(Subscriber criteria);

}
