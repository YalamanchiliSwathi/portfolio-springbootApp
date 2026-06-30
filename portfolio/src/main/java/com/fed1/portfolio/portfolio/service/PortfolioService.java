package com.fed1.portfolio.portfolio.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fed1.portfolio.portfolio.model.DailyReturnRequest;
import com.fed1.portfolio.portfolio.model.DailyReturnResponse;

@Service
public class PortfolioService {
	
	public DailyReturnResponse calculate(DailyReturnRequest req) {

        DailyReturnResponse res = new DailyReturnResponse();
        List<String> reasons = new ArrayList<>();

        res.setPortfolioId(req.getPortfolioId());
        res.setValuationDate(req.getValuationDate());
        res.setBenchmarkReturnPct(req.getBenchmarkReturnPct());

        // Rule 1
        if (req.getBeginMarketValue() < 0 || req.getEndMarketValue() < 0) {
            res.setStatus("INVALID_INPUT");
            reasons.add("Market value cannot be negative");
            res.setReasons(reasons);
            return res;
        }

        // Rule 2
        if (req.getCurrency() == null || req.getCurrency().isEmpty()) {
            res.setStatus("INVALID_INPUT");
            reasons.add("Currency missing");
            res.setReasons(reasons);
            return res;
        }

        // Rule 3 & 4
        if (req.getBeginMarketValue() == 0 && req.getEndMarketValue() != 0) {
            res.setStatus("INVALID_INPUT");
            reasons.add("Invalid begin value");
            res.setReasons(reasons);
            return res;
        }

        double portfolioReturn =
                ((req.getEndMarketValue()
                        - req.getBeginMarketValue()
                        - req.getNetCashFlow())
                        / req.getBeginMarketValue()) * 100;

        res.setPortfolioReturnPct(portfolioReturn);

        double excess =
                portfolioReturn - req.getBenchmarkReturnPct();

        res.setExcessReturnPct(excess);

        // Rule 5
        if (Math.abs(excess) > 5) {
            reasons.add("Return difference > 5%");
        }

        // Rule 6
        if (Math.abs(req.getNetCashFlow()) >
                (req.getBeginMarketValue() * 0.2)) {
            reasons.add("Cash flow > 20%");
        }

        if (reasons.isEmpty()) {
            res.setStatus("VALID");
        } else {
            res.setStatus("REVIEW_REQUIRED");
        }

        res.setReasons(reasons);
        res.setProcessedAt(LocalDateTime.now().toString());

        return res;
    }
}