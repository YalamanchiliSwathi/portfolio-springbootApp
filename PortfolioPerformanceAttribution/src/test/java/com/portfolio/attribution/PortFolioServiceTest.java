package com.portfolio.attribution;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.portfolio.attribution.exception.InvalidInputException;
import com.portfolio.attribution.model.AttributionRequest;
import com.portfolio.attribution.model.AttributionResponse;
import com.portfolio.attribution.model.Group;
import com.portfolio.attribution.service.PortfolioServiceImpl;


@ExtendWith(MockitoExtension.class)
class PortfolioServiceTest {

    @InjectMocks
    private PortfolioServiceImpl portfolioService;

    @Test
    void testCalculate_NullRequest() {
        assertThrows(InvalidInputException.class, () -> {
            portfolioService.calculate(null);
        });
    }

    @Test
    void testCalculate_NullGroups() {
        AttributionRequest request = new AttributionRequest();
        request.setGroups(null);

        assertThrows(InvalidInputException.class, () -> {
            portfolioService.calculate(request);
        });
    }

    @Test
    void testCalculate_InvalidWeightLow() {
        AttributionRequest request = new AttributionRequest();

        Group g1 = new Group();
        g1.setWeightPct(50);

        request.setGroups(Arrays.asList(g1));

        assertThrows(InvalidInputException.class, () -> {
            portfolioService.calculate(request);
        });
    }

    @Test
    void testCalculate_InvalidWeightHigh() {
        AttributionRequest request = new AttributionRequest();

        Group g1 = new Group();
        g1.setWeightPct(120);

        request.setGroups(Arrays.asList(g1));

        assertThrows(InvalidInputException.class, () -> {
            portfolioService.calculate(request);
        });
    }

    @Test
    void testCalculate_ValidInput() {
        AttributionRequest request = new AttributionRequest();

        Group group = new Group();
        group.setGroupName("Debt");
        group.setWeightPct(100);
        group.setReturnPct(10.0);

        request.setGroups(Arrays.asList(group));

        AttributionResponse response = portfolioService.calculate(request);

        assertNotNull(response);
        assertEquals("VALID", response.getStatus());
    }
}