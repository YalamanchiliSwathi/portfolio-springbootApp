package com.fed1.portfolio.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fed1.portfolio.portfolio.model.DailyReturnRequest;
import com.fed1.portfolio.portfolio.model.DailyReturnResponse;
import com.fed1.portfolio.portfolio.service.PortfolioService;

@RestController
@RequestMapping("/api/performance")
public class PortfolioController {
	
	@Autowired
    private PortfolioService service;

    @PostMapping("/daily-return")
    public DailyReturnResponse calculate(
            @RequestBody DailyReturnRequest request) {

        return service.calculate(request);
    }


}
