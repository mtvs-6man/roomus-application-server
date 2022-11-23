package com.sixman.roomus.product.query.model;

import com.sixman.roomus.commons.jpa.MoneyConverter;
import com.sixman.roomus.commons.model.Money;
import com.sixman.roomus.product.command.domain.model.vo.ProductScale;
import lombok.*;

import javax.persistence.*;
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

    @Column(name = "INFORMATION")
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

    @OneToMany
    @JoinColumn(name = "PRODUCT_NO")
    private List<ProductLikesMemberData> ProductLikesMember;

//    public ProductData(Integer memberNo,String funitureName, Boolean location, String category, String information, Float xSize, Float ySize, Float zSize, Integer price, Date createdDate, Date lastModifiedDate, String fileUrl, String screenShotUrl, int countLike, boolean isDelete) {
//        this.memberNo = memberNo;
//        this.funitureName = funitureName;
//        this.location = location;
//        this.category = category;
//        this.information = information;
//        this.productScale = new ProductScale(xSize, ySize, zSize);
//        this.price = new Money(price);
//        this.createdDate = createdDate;
//        this.lastModifiedDate = lastModifiedDate;
//        this.fileUrl = fileUrl;
//        this.screenShotUrl = screenShotUrl;
//        this.countLikes = countLike;
//        this.isDelete = isDelete;
//    }
}