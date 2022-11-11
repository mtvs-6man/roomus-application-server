package com.sixman.roomus.product.command.domain.model;

import com.sixman.roomus.commons.jpa.MoneyConverter;
import com.sixman.roomus.commons.model.Money;
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
@SequenceGenerator(
        name = "PRODUCT_SEQUENCE", // 식별자 생성 이름
        sequenceName = "PRODUCT_NO_SEQ", // 데이터 베이스 시퀀스 이름 기본값은 hibernate_sequence 이다
        initialValue = 1, //ddl 생성 시에만 사용되며 ddl을 생성할 때 처음 시작하는 수로 기본 값은 1이다.
        allocationSize = 1  //allocationSize 시퀀스 한 번 호출로 증가하는 수로 기본값은 50이다.
        // catalog, schema 데이터 베이스 catelog, schema 이름
        // 매핑할 ddl
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQUENCE")
    @Column(name = "PRODUCT_NO")
    private Integer productNo;

    @Column(name = "MEMBER_NO")
    private Integer memberNo;

    @Column(name = "FUNITURE_NAME")
    private String funitureName;

    @Column(name = "LOCATION")
    private Boolean location;

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

    @OneToMany(mappedBy = "productLikesMemberPk.product", fetch = FetchType.EAGER)
    private List<ProductLikesMember> ProductLikesMember;

    public Product(Integer memberNo,String funitureName, Boolean location, String category, String information, Float xSize, Float ySize, Float zSize, Integer price, Date createdDate, Date lastModifiedDate, String fileUrl, String screenShotUrl) {
        this.memberNo = memberNo;
        this.funitureName = funitureName;
        this.location = location;
        this.category = category;
        this.information = information;
        this.productScale = new ProductScale(xSize, ySize, zSize);
        this.price = new Money(price);
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.fileUrl = fileUrl;
        this.screenShotUrl = screenShotUrl;
    }
}