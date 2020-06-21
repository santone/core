package com.global.savings.core.price;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
abstract class PriceConstant {
    static final String DE = "DE";
    static final String FR = "FR";
    static final String UNKNOWN = "UNKNOWN";
}
