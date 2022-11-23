package com.sixman.roomus.rooms.command.domain.model.vo;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Float4 implements Serializable {
    @ColumnDefault("1.0")
    private float x;
    @ColumnDefault("1.0")
    private float y;
    @ColumnDefault("1.0")
    private float z;
    @ColumnDefault("0.0")
    private float w;

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Float4 float4 = (Float4) obj;
        return this.x == float4.x && this.y == float4.y && this.z == float4.z && this.w == float4.w;
    }
}
