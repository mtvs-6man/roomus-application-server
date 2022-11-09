package com.sixman.roomus.commons.jpa;

import com.sixman.roomus.commons.model.Money;

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
