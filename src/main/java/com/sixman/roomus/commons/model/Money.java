package com.sixman.roomus.commons.model;


import com.sixman.roomus.commons.exception.MoneyCanNotNagativeNumberException;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class Money {

    private int value;

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }

    public Money(int value) {
        if (value < 0){
            throw new MoneyCanNotNagativeNumberException("돈은 음수가 될 수 없습니다.");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
