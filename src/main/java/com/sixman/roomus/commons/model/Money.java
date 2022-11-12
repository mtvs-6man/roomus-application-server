package com.sixman.roomus.commons.model;


import com.sixman.roomus.commons.exception.MoneyCanNotNagativeNumberException;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class Money {

    private Integer value;

    public Money multiply(Integer multiplier) {
        return new Money(value * multiplier);
    }

    public Money(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
