package com.global.savings.core.price;

import com.global.savings.core.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static com.global.savings.core.price.PriceConstant.DE;
import static com.global.savings.core.price.PriceConstant.FR;
import static com.global.savings.core.price.PriceConstant.UNKNOWN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


public class PriceServiceTest {
    private PriceService priceService;

    @Spy
    private TaxRateProvider taxRateProvider;

    @BeforeEach
    public void init() {
        initMocks(this);
        priceService = new PriceService(taxRateProvider);
    }

    @Test
    public void testWhenNetPriceShouldBeInteger() {
        assertEquals(81, priceService.calculateNetPrice(100, DE));
        verify(taxRateProvider).tax(DE);
    }

    @Test
    public void testWhenNetPriceShouldBeRounded() {
        assertEquals(1.6, priceService.calculateNetPrice(1.99, FR));
        verify(taxRateProvider).tax(FR);
    }

    @Test
    public void testWhenCountryIsUnknown() {
        assertThrows(ApplicationException.class, () -> priceService.calculateNetPrice(1.99, UNKNOWN));
        verify(taxRateProvider).tax(UNKNOWN);
    }

    @Test
    public void testWhenCountryIsNull() {
        assertThrows(ApplicationException.class, () -> priceService.calculateNetPrice(1.99, null));
        verify(taxRateProvider, never()).tax(any());
    }

    @Test
    public void testWhenGrossIsZero() {
        assertThrows(ApplicationException.class, () -> priceService.calculateNetPrice(0, UNKNOWN));
        verify(taxRateProvider, never()).tax(any());
    }

    @Test
    public void testWhenGrossIsMinus() {
        assertThrows(ApplicationException.class, () -> priceService.calculateNetPrice(-1, UNKNOWN));
        verify(taxRateProvider, never()).tax(any());
    }
}
