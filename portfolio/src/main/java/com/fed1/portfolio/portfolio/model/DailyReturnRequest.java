package com.fed1.portfolio.portfolio.model;

import lombok.Data;


@Data
public class DailyReturnRequest {

	private String portfolioId;
    private String valuationDate;
    private double beginMarketValue;
    private double endMarketValue;
    private double netCashFlow;
    private double benchmarkReturnPct;
    private String currency;
    private String requestedBy;
	
	}


