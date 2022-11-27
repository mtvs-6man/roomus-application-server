package com.sixman.roomus.product.query.model;

import com.sixman.roomus.commons.jpa.MoneyConverter;
import com.sixman.roomus.commons.model.Money;
import com.sixman.roomus.product.command.domain.model.vo.ProductScale;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TBL_PRODUCT")
public class ProductData {

    @Id
    @Column(name = "PRODUCT_NO")
    private Integer productNo;

    @Column(name = "MEMBER_NO")
    private Integer memberNo;

    @Column(name = "FUNITURE_NAME")
    private String funitureName;

    @Column(name = "LOCATION")
    private boolean location;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "INFORMATION", columnDefinition = "TEXT")
    private String information;

    @Embedded
    private ProductScale productScale;
    @Convert(converter = MoneyConverter.class)
    @Column(name = "PRODUCT_PRICE")
    private Money price;

    // 생성일
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    // 마지막 수정일
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Column(name = "FILE_URL")
    private String fileUrl;

    @Column(name = "SCREENT_SHOT_URL")
    private String screenShotUrl;

    @Column(name = "IS_DELETE")
    private boolean isDelete;

    @OneToMany(mappedBy = "productLikesMemberPk.productData")
    private List<ProductLikesMemberData> productLikesMember = new ArrayList<>();

    @OneToMany(mappedBy = "productData")
    private List<ProductCommentData> productCommentData = new ArrayList<>();

}