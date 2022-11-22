package com.sixman.roomus.rooms.command.domain.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Color implements Serializable {
    @ColumnDefault("0.0")
    private float r;
    @ColumnDefault("0.0")
    private float g;
    @ColumnDefault("0.0")
    private float b;
    @ColumnDefault("0.0")
    private float a;

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b, a);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Color color = (Color) obj;
        return this.r == color.r && this.g == color.g && this.b == color.b && this.a == color.a;
    }
}
