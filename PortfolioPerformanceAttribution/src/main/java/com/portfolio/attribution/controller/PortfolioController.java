package com.portfolio.attribution.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.attribution.model.AttributionRequest;
import com.portfolio.attribution.model.AttributionResponse;
import com.portfolio.attribution.service.PortfolioService;

@RestController
@RequestMapping("/api/performance")
public class  PortfolioController {

	private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/attribution")
    public AttributionResponse calculate(
            @RequestBody AttributionRequest request) {
        return portfolioService.calculate(request);
    }
}