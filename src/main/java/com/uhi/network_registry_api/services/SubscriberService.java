package com.uhi.network_registry_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uhi.network_registry_api.exceptions.NRAuthException;
import com.uhi.network_registry_api.models.Subscriber;
import com.uhi.network_registry_api.repository.SubscriberRepository;

@Service
@Transactional
public class SubscriberService implements ISubscriberService {

	@Autowired
	SubscriberRepository subscriberRepository;

	@Override
	public Subscriber validateSubscriber(String subscriber_id, String unique_key_id, String pub_key_id)
			throws NRAuthException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscriber addSubscriber(String city, String encr_public_key, String pub_key_id, String signing_public_key,
			String subscriber_id, String unique_key_id, String sub_type, String url) throws NRAuthException {

		if (subscriber_id != null) {
			Subscriber s = subscriberRepository.findSubscriber(subscriber_id);
			if (s != null) {
				throw new NRAuthException("Subscriber ID already exists");
			}

			Integer participant_id = subscriberRepository.addSubscriber(subscriber_id, url, city, url, unique_key_id,
					pub_key_id, signing_public_key, encr_public_key, city, encr_public_key, pub_key_id,
					signing_public_key, subscriber_id, unique_key_id, sub_type, url);
			return subscriberRepository.findSubscriberByParticipantid(participant_id);

		}

		return null;
	}

	@Override
	public Subscriber updateSubscriber(Integer participant_id, String city, String encr_public_key, String pub_key_id,
			String signing_public_key, String subscriber_id, String unique_key_id, String url) throws NRAuthException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscriber findSubscriber(String subscriber_id) {

		return subscriberRepository.findSubscriber(subscriber_id);

	}

	@Override
	public List<Subscriber> lookup(Subscriber criteria) {
		List<Subscriber> result = null;
		if (criteria != null) {
			result = subscriberRepository.lookup(criteria);

		}
		return result;
	}

}
