package com.global.savings.core.price;

import com.global.savings.core.exception.ApplicationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
@SuppressWarnings("PMD.DefaultPackage")
public class TaxRateProvider {
    private static final Map<String, BigDecimal> TAX_BY_COUNTRY = Map.of("DE", new BigDecimal("0.19"),
        "FR", new BigDecimal("0.20"));

    /**
     * It returns tax connecting to the country in iso.
     * It throws ApplicationException when country not found.
     * @param countryIso like DE, FR, ...
     * @return tax in BigDecimal when country found, otherwise it throws ApplicationException
     */
    BigDecimal tax(final String countryIso) {
        if (countryIso == null) {
            throw new ApplicationException("Country can not be null");
        }

        return Optional.ofNullable(TAX_BY_COUNTRY.get(countryIso)).orElseThrow(() ->
            new ApplicationException("Not found country: " + countryIso));
    }
}

