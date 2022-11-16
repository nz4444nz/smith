package com.lening.daylast.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "tb_goods")
public class GoodsBean implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;
    private String gname;
    private Double gprice;
    @Transient
    private Double egprice;

    private String gaddress;

    private Long sid;
    private Long id;

    @Transient
    private SellerBean sellerBean = new SellerBean();

    @Transient
    private BrandBean brandBean = new BrandBean();
}
