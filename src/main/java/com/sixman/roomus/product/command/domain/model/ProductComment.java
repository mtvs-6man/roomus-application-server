package com.sixman.roomus.product.command.domain.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@SequenceGenerator(
        name = "PRODUCT_COMMENT_SEQUENCE",
        sequenceName = "PRODUCT_COMMENT_NO_SEQ"
)
@Entity
@Table(name = "PRODUCT_COMMENT")
public class ProductComment {

    @Id
    @Column(name = "PRODUCT_COMMENT_NO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_COMMENT_SEQUENCE")
    private int productCommentNo;

    @Column(name = "MEMBER_NO")
    @NonNull
    private int memberNo;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_NO")
    @NonNull
    private Product product;

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


