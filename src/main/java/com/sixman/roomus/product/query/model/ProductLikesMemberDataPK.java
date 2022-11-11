package com.sixman.roomus.product.query.model;

import com.sixman.roomus.product.command.domain.model.Product;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductLikesMemberDataPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "PRODUCT_NO")
    private Product product;

    @Column(name = "MEMBER_NO")
    private int memberId;

}
