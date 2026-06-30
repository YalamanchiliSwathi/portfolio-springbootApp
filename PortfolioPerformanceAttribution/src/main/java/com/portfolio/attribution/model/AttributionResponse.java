package com.portfolio.attribution.model;

import java.util.List;

import lombok.Data;

@Data
public class AttributionResponse {
	private String requestId;
    private String portfolioId;
    private String valuationDate;
    private double totalContributionPct;
    private List<GroupContribution> groupContributions;
    private String status;
    private boolean degraded;
    private List<String> warnings;
    private String processedAt;

}
