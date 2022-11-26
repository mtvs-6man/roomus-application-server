package com.sixman.roomus.product.command.domain.model.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor()
@Getter
@Setter
public class ProductScale implements Serializable {

    @Column(name = "X_SIZE")
    private Float xSize;
    @Column(name = "Y_SIZE")
    private Float ySize;
    @Column(name = "Z_SIZE")
    private Float zSize;

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
