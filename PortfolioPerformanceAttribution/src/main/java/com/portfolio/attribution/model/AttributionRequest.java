package com.portfolio.attribution.model;

import java.util.List;

import lombok.Data;

@Data
public class AttributionRequest {

	private String requestId;
    private String portfolioId;
    private String valuationDate;
    private List<Group> groups;
    private String currency;
    private String requestedBy;

}
