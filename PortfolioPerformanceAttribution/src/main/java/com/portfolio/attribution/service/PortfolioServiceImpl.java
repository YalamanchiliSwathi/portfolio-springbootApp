package com.portfolio.attribution.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.portfolio.attribution.exception.InvalidInputException;
import com.portfolio.attribution.model.AttributionRequest;
import com.portfolio.attribution.model.AttributionResponse;
import com.portfolio.attribution.model.Group;
import com.portfolio.attribution.model.GroupContribution;

@Service
public class PortfolioServiceImpl implements PortfolioService{

	@Override
    public AttributionResponse calculate(AttributionRequest request) {

		if(request == null || request.getGroups() == null) {
			throw new InvalidInputException("Request or groups can't be null");
		}
		
        double totalWeight = request.getGroups()
                .stream()
                .mapToDouble(Group::getWeightPct)
                .sum();

        if (totalWeight < 99 || totalWeight > 101) {
            throw new InvalidInputException("INVALID_INPUT");
        }

        double totalContribution = 0;
        int missingCount = 0;
        boolean degraded = false;

        List<String> warnings = new ArrayList<>();
        List<GroupContribution> contributions = new ArrayList<>();

        for (Group group : request.getGroups()) {

            GroupContribution gc = new GroupContribution();
            gc.setGroupName(group.getGroupName());

            Double returnPct = group.getReturnPct();

            if (returnPct == null) {
                if (group.getFallbackReturnPct() != null) {
                    returnPct = group.getFallbackReturnPct();
                    gc.setPricingMode("FALLBACK_USED");
                    warnings.add("Fallback pricing used for " + group.getGroupName());
                } else {
                    missingCount++;
                    degraded = true;
                    warnings.add("Missing return for " + group.getGroupName());
                    continue;
                }
            } else {
                gc.setPricingMode("PRIMARY");
            }

            double contribution =
                    group.getWeightPct() * returnPct / 100;

            gc.setContributionPct(contribution);
            totalContribution += contribution;
            contributions.add(gc);
        }

        AttributionResponse response = new AttributionResponse();
        response.setRequestId(request.getRequestId());
        response.setPortfolioId(request.getPortfolioId());
        response.setValuationDate(request.getValuationDate());
        response.setTotalContributionPct(totalContribution);
        response.setGroupContributions(contributions);
        response.setWarnings(warnings);
        response.setDegraded(degraded);
        response.setProcessedAt(LocalDateTime.now().toString());

        if (missingCount > 1)
            response.setStatus("REVIEW_REQUIRED");
        else if (degraded)
            response.setStatus("DEGRADED");
        else
            response.setStatus("VALID");

        return response;
    
	}
}