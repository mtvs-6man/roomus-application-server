package com.sixman.roomus.product.query.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@ToString(exclude = {"productData"})
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "TBL_PRODUCT_COMMENT")
public class ProductCommentData {

    @Id
    @Column(name = "PRODUCT_COMMENT_NO")
    private int productCommentNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_NO")
    private ProductData productData;

    @Column(name = "MEMBER_NO")
    @NonNull
    private int memberNo;

    @Column(name = "COMMENT")
    @NonNull
    private String comment;

    @Column(name = "IS_DELETE")
    @NonNull
    private boolean isDelete;

    @Column(name = "DELETE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteDate;

    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NonNull
    private Date lastModifiedDate;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NonNull
    private Date createDate;
}


