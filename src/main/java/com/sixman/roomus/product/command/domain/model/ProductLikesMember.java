package com.sixman.roomus.product.command.domain.model;

import com.sixman.roomus.product.command.domain.model.vo.ProductLikesMemberPK;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TBL_PRODUCT_LIKES_MEMBER")
public class ProductLikesMember {

    @EmbeddedId
    private ProductLikesMemberPK productLikesMemberPk;

}
