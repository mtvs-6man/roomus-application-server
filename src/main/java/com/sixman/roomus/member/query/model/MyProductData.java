package com.sixman.roomus.member.query.model;

import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.query.dto.MyProductDTO;
import com.sixman.roomus.member.query.dto.UserSerchDTO;
import com.sixman.roomus.product.command.domain.model.Product;
import com.sixman.roomus.product.query.model.ProductData;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedNativeQuery(
        name = "SelectMyProduct",
        query = "select\n" +
                "    tp.product_no as productNo,\n" +
                "    tp.category,\n" +
                "    tp.file_url as fileUrl,\n" +
                "    tp.funiture_name as funitureName,\n" +
                "    tp.product_price as productPrice\n" +
                "from tbl_myproduct p\n" +
                "left join tbl_product tp on tp.product_no = p.product_no\n" +
                "where p.member_no= :memberNo",
        resultSetMapping = "MyproductDTO"
)
@SqlResultSetMapping(
        name = "MyproductDTO",
        classes = @ConstructorResult(
                targetClass = MyProductDTO.class,
                columns = {
                        @ColumnResult(name = "productNo", type = Integer.class),
                        @ColumnResult(name = "category", type = String.class),
                        @ColumnResult(name = "fileUrl", type = String.class),
                        @ColumnResult(name = "funitureName", type = String.class),
                        @ColumnResult(name = "productPrice", type = Integer.class)
                }
        )
)
@Table(name = "TBL_MYPRODUCT")
public class MyProductData {

    @Id
    @Column(name = "MYPRODUCT_ID")
    private int myProductId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private MemberData memberNo;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_NO")
    private ProductData productNo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registDate;

    public MyProductData() {
    }


    public MyProductData(int myProductId, MemberData memberNo, ProductData productNo, Date registDate) {
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

    public MemberData getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(MemberData memberNo) {
        this.memberNo = memberNo;
    }

    public ProductData getProductNo() {
        return productNo;
    }

    public void setProductNo(ProductData productNo) {
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
        return "MyProductData{" +
                "myProductId=" + myProductId +
                ", memberNo=" + memberNo +
                ", productNo=" + productNo +
                ", registDate=" + registDate +
                '}';
    }
}
