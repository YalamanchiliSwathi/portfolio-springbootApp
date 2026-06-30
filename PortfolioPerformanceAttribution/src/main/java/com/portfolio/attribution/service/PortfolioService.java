package com.portfolio.attribution.service;

import com.portfolio.attribution.model.AttributionRequest;
import com.portfolio.attribution.model.AttributionResponse;

public interface PortfolioService {
	
	AttributionResponse calculate(AttributionRequest request);

}
