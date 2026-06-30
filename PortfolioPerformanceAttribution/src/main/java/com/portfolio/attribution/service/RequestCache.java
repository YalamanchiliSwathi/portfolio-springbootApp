package com.portfolio.attribution.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.portfolio.attribution.model.AttributionResponse;

@Component
public class RequestCache {

	private final ConcurrentHashMap<String, AttributionResponse> cache =
            new ConcurrentHashMap<>();

    public boolean exists(String requestId) {
        return cache.containsKey(requestId);
    }

    public AttributionResponse get(String requestId) {
        return cache.get(requestId);
    }

    public void save(String requestId, AttributionResponse response) {
        cache.put(requestId, response);
    }

	
	
}
