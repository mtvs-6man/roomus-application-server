package com.sixman.roomus.product.query.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductLikesMemberDataPK implements Serializable {

    @Column(name = "MEMBER_NO")
    private int memberId;

}
