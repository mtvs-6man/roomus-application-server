package com.sixman.roomus.product.command.domain.model.vo;

import com.sixman.roomus.product.command.domain.model.Product;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private int memberNo;

    @Override
    public int hashCode() {
        return Objects.hash(product.hashCode(), memberNo);
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
        return product == productLikesMemberPK.getProduct() && Objects.equals(memberNo, productLikesMemberPK.getMemberNo());
    }
}
