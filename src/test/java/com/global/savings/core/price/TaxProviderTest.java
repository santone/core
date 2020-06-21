package com.global.savings.core.price;

import com.global.savings.core.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static com.global.savings.core.price.PriceConstant.DE;
import static com.global.savings.core.price.PriceConstant.UNKNOWN;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.util.Assert.notNull;

public class TaxProviderTest {

    @Spy
    private TaxRateProvider taxRateProvider;

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    @Test
    public void testForValidCountry() {
        notNull(taxRateProvider.tax(DE), "It should contain tax for Germany");
    }

    @Test
    public void testForUnExistCountry() {
        assertThrows(ApplicationException.class, () -> taxRateProvider.tax(UNKNOWN));
    }

    @Test
    public void testForNullCountry() {
        assertThrows(ApplicationException.class, () -> taxRateProvider.tax(null));
    }
}
