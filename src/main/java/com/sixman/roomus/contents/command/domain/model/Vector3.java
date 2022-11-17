package com.sixman.roomus.contents.command.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor()
@AllArgsConstructor()
@Setter
@Getter
@ToString
public class Vector3 implements Serializable {
    @Column(name = "x")
    private float x;
    @Column(name = "y")
    private float y;
    @Column(name = "z")
    private float z;

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector3 objVector3 = (Vector3) obj;
        return this.x == objVector3.x && this.y == objVector3.y && this.z == objVector3.y;
    }
}
