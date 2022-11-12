package com.sixman.roomus.product.query.model;

import com.sixman.roomus.product.command.domain.model.Product;
import lombok.*;

import javax.persistence.*;
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
