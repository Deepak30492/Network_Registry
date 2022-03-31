package com.uhi.network_registry_api.controllers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Crypt {

	public String generateBlakeHash(String req) {
		byte[] test = digest_tool("BLAKE2B-512", req);
		return toBase64(test);

	}

	public byte[] digest_tool(String algorithm, String payload) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm, BouncyCastleProvider.PROVIDER_NAME);
			digest.reset();
			digest.update(payload.getBytes(StandardCharsets.UTF_8));
			return digest.digest();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public String toBase64(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

}
