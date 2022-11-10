package com.sixman.roomus.product.query.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor()
@Getter
public class ProductScaleData implements Serializable {

    @Column(name = "X_SIZE")
    private float xsize;
    @Column(name = "Y_SIZE")
    private float ysize;
    @Column(name = "Z_SIZE")
    private float zsize;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ProductId orderNo = (ProductId) o;
//        return Objects.equals(id, orderNo.id);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }

}
