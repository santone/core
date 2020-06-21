package com.global.savings.core.price;

import com.global.savings.core.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class PriceService {
    private static final int SCALE = 1;
    private final TaxRateProvider taxRateProvider;

    /**
     * It calculates the net price from grossPrice and countryIso.
     * Firstly, we retrieve the tax by the country.
     *
     * net price = gross price - gross price * tax
     *
     * Example:
     * 81  = calculateNetPrice(100, DE); when tax is 0.19
     * 1.6 = calculateNetPrice(1.99, FR); when tax is 0.20
     *
     *
     * @param grossPrice
     *      it is a double value representing a gross price
     * @param countryIso
     *      it is a string representing a country like: "DE"
     * @return
     *      gross price - gross price * tax  => it is a rounded value
     */
    public double calculateNetPrice(final double grossPrice, final String countryIso) {
        if (grossPrice <= 0) {
            throw new ApplicationException("The gross price should be greater than 0");
        }

        if (countryIso == null) {
            throw new ApplicationException("Country can not be null");
        }

        return calculateNetPrice(BigDecimal.valueOf(grossPrice),
            taxRateProvider.tax(countryIso)).doubleValue();
    }

    private static BigDecimal calculateNetPrice(final BigDecimal decimalGrossPrice, final BigDecimal decimalTax) {
        final BigDecimal decimalNetPrice = decimalGrossPrice.add(decimalGrossPrice.negate().multiply(decimalTax));
        return decimalNetPrice.setScale(SCALE, RoundingMode.HALF_UP);
    }
}
