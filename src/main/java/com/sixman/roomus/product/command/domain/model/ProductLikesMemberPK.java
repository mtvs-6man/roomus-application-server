package com.sixman.roomus.product.command.domain.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductLikesMemberPK implements Serializable {

    @JoinColumn(name = "PRODUCT_NO")
    private Product product;

    @Column(name = "MEMBER_NO")
    private int memberId;

}
