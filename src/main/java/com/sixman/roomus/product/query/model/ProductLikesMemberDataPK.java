package com.sixman.roomus.product.query.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"productData"})
public class ProductLikesMemberDataPK implements Serializable {

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_NO")
    private ProductData productData;

}
