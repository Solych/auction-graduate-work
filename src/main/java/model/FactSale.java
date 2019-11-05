package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FACT_SALE", schema = "auctionSchema")
public class FactSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FACT_SALE_ID")
    private Integer factSaleId;

    @OneToOne
    @JoinColumn(name = "BUYER_ID")
    private Buyer buyer;

    @OneToOne
    @JoinColumn(name = "THING_ID")
    private Thing thing;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SALE_TIME")
    private Date saleTime;

    @Column(name = "PRICE")
    private Integer price;

    public Integer getFactSaleId() {
        return factSaleId;
    }

    public void setFactSaleId(Integer factSaleId) {
        this.factSaleId = factSaleId;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public FactSale(Buyer buyer, Thing thing, Date saleTime, Integer price) {
        this.buyer = buyer;
        this.thing = thing;
        this.saleTime = saleTime;
        this.price = price;
    }
}
