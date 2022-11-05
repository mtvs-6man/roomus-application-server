package com.sixman.roomus.product.command.domain.model;

import com.sixman.roomus.commons.jpa.MoneyConverter;
import com.sixman.roomus.commons.model.Money;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private Integer no;

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

    public Product(String funitureName, boolean location, String category, String information, float xSize, float ySize, float zSize, int price, Date createdDate, Date lastModifiedDate) {
        this.funitureName = funitureName;
        this.location = location;
        this.category = category;
        this.information = information;
        this.productScale = new ProductScale(xSize, ySize, zSize);
        this.price = new Money(price);
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}