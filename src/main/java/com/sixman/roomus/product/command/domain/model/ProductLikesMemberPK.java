package com.sixman.roomus.product.command.domain.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductLikesMemberPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "PRODUCT_NO")
    private Product product;

    @Column(name = "MEMBER_NO")
    private int memberId;

    @Override
    public int hashCode() {
        return Objects.hash(product.hashCode(), memberId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProductLikesMemberPK productLikesMemberPK = (ProductLikesMemberPK) obj;
        return product == productLikesMemberPK.getProduct() && Objects.equals(memberId, productLikesMemberPK.getMemberId());
    }
}
