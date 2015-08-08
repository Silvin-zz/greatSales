package com.example.pikino.greatsales;

/**
 * Created by pikino on 08/08/15.
 */
public class SaleDetail {

    private Long  id;
    private Long  saleId;
    private Long  productId;
    private Long  count;
    private Float cost;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "SaleDetail{" +
                "id=" + id +
                ", saleId=" + saleId +
                ", productId=" + productId +
                ", count=" + count +
                ", cost=" + cost +
                '}';
    }
}
