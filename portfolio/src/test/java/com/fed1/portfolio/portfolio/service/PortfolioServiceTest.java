package com.fed1.portfolio.portfolio.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fed1.portfolio.portfolio.model.DailyReturnRequest;
import com.fed1.portfolio.portfolio.model.DailyReturnResponse;

class PortfolioServiceTest {

	PortfolioService portfolioService = new PortfolioService();
	
	@Test
	public void testValidCase() {
		DailyReturnRequest dailyReturnRequest = new DailyReturnRequest();
		
		dailyReturnRequest.setPortfolioId("PF-1001");
		dailyReturnRequest.setValuationDate("2026-06-27");
		dailyReturnRequest.setBeginMarketValue(100000);
		dailyReturnRequest.setEndMarketValue(103500);
		dailyReturnRequest.setNetCashFlow(1000);
		dailyReturnRequest.setBenchmarkReturnPct(1.8);
		dailyReturnRequest.setCurrency("USD");

		DailyReturnResponse dailyReturnResponse = portfolioService.calculate(dailyReturnRequest);
		
		assertEquals("VALID", dailyReturnResponse.getStatus());
		
	}
		@Test
		public void testValidCasee() {
			DailyReturnRequest dailyReturnRequest = new DailyReturnRequest();
			
			dailyReturnRequest.setPortfolioId("PF-1001");
			dailyReturnRequest.setValuationDate("2026-06-27");
			dailyReturnRequest.setBeginMarketValue(100000);
			dailyReturnRequest.setEndMarketValue(103500);
			dailyReturnRequest.setNetCashFlow(1000);
			dailyReturnRequest.setBenchmarkReturnPct(1.8);
			dailyReturnRequest.setCurrency("USD");
			
			DailyReturnResponse dailyReturnResponse = portfolioService.calculate(dailyReturnRequest);
			assertEquals("VALID", dailyReturnResponse.getStatus());
			
		}
		
		@Test
		public void testReviewCase() {
			DailyReturnRequest dailyReturnRequest = new DailyReturnRequest();
			
			dailyReturnRequest.setPortfolioId("PF-1001");
			dailyReturnRequest.setValuationDate("2026-06-27");
			dailyReturnRequest.setBeginMarketValue(100000);
			dailyReturnRequest.setEndMarketValue(150000);
			dailyReturnRequest.setNetCashFlow(50000);
			dailyReturnRequest.setBenchmarkReturnPct(1.0);
			dailyReturnRequest.setCurrency("USD");
			
			DailyReturnResponse dailyReturnResponse =  portfolioService.calculate(dailyReturnRequest);
			assertEquals("REVIEW_REQUIRED", dailyReturnResponse.getStatus());
			
		}
	
}