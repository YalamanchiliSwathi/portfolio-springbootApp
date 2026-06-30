package com.fed1.portfolio.portfolio.model;

import java.util.List;

import lombok.Data;

@Data
public class DailyReturnResponse {
	private String portfolioId;
    private String valuationDate;
    private double portfolioReturnPct;
    private double benchmarkReturnPct;
    private double excessReturnPct;
    private String status;
    private List<String> reasons;
    private String processedAt;


}
