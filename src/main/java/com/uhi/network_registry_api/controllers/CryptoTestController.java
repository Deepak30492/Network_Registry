package com.uhi.network_registry_api.controllers;

import java.util.HashMap;
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
import com.fasterxml.jackson.databind.ObjectWriter;
import com.uhi.network_registry_api.models.Subscriber;
import com.uhi.network_registry_api.services.SubscriberService;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCXDHPublicKey;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;

@RestController
@RequestMapping("/api")
public class CryptoTestController {
	
	@Autowired
	SubscriberService subscriberService;

		
	@PostMapping("/crypt")
	public String subscribeParticipant(@RequestBody Map<String, Object> userMap)
	{
		Crypt newCrypt = new Crypt();
		String payload = "{\n" +
                "    \"context\": {\n" +
                "      \"domain\": \"test\",\n" +
                "      \"country\": \"string\",\n" +
                "      \"city\": \"string\",\n" +
                "      \"action\": \"on_support\",\n" +
                "      \"core_version\": \"string\",\n" +
                "      \"bap_id\": \"string\",\n" +
                "      \"bap_uri\": \"string\",\n" +
                "      \"bpp_id\": \"string\",\n" +
                "      \"bpp_uri\": \"string\",\n" +
                "      \"transaction_id\": \"string\",\n" +
                "      \"message_id\": \"string\",\n" +
                "      \"timestamp\": \"2021-03-30T12:25:31.333Z\",\n" +
                "      \"key\": \"string\",\n" +
                "      \"ttl\": \"string\"\n" +
                "    },\n" +
                "    \"message\": {\n" +
                "      \"phone\": \"string\",\n" +
                "      \"email\": \"user@example.com\",\n" +
                "      \"uri\": \"string\"\n" +
                "    }\n" +
                "  }\n";
		
		
		String value = newCrypt.generateBlakeHash(payload);
		return value;
	}
	
	
}
