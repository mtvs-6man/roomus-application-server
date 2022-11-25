package com.sixman.roomus.member.command.domain.model;

import com.sixman.roomus.product.command.domain.model.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "TBL_MYPRODUCT")
@SequenceGenerator(
        name = "MYPRODUCT_SEQUENCE",
        sequenceName = "MYPRODUCT_NO_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class MyProduct {

    @Id
    @Column(name = "MYPRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MYPRODUCT_SEQUENCE")
    private int myProductId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member memberNo;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_NO")
    private Product productNo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registDate;

    public MyProduct() {
    }

    public MyProduct(int myProductId, Member memberNo, Product productNo, Date registDate) {
        this.myProductId = myProductId;
        this.memberNo = memberNo;
        this.productNo = productNo;
        this.registDate = registDate;
    }

    public int getMyProductId() {
        return myProductId;
    }

    public void setMyProductId(int myProductId) {
        this.myProductId = myProductId;
    }

    public Member getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Member memberNo) {
        this.memberNo = memberNo;
    }

    public Product getProductNo() {
        return productNo;
    }

    public void setProductNo(Product productNo) {
        this.productNo = productNo;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    @Override
    public String toString() {
        return "MyProduct{" +
                "myProductId=" + myProductId +
                ", memberNo=" + memberNo +
                ", productNo=" + productNo +
                ", registDate=" + registDate +
                '}';
    }
}
