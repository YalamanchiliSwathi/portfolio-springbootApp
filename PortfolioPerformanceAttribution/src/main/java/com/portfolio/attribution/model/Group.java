package com.portfolio.attribution.model;

import lombok.Data;

@Data
public class Group {
	
	private String groupName;
	private double weightPct;
	private Double returnPct;
	
	private Double fallbackReturnPct;
	
	

}
