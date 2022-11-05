package com.sixman.roomus.common.jpa;

import com.sixman.roomus.common.model.Money;

import javax.persistence.AttributeConverter;

public class MoneyConverter implements AttributeConverter<Money, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Money money) {
        return money != null ? money.getValue() : null;
    }

    @Override
    public Money convertToEntityAttribute(Integer value) {
        return value != null ? new Money(value) : null;
    }
}
