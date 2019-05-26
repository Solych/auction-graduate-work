package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FACT_OVERRIDE", schema = "auction")
public class FactOverride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FACT_OVERRIDE_ID")
    private Integer factOverrideId;

    @ManyToOne
    @JoinColumn(name = "BUYER_ID")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "THING_ID")
    private Thing thing;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OVERRIDE_TIME")
    private Date overrideTime;

    @Column(name = "PRICE")
    private Integer price;

    public Integer getFactOverrideId() {
        return factOverrideId;
    }

    public void setFactOverrideId(Integer factOverrideId) {
        this.factOverrideId = factOverrideId;
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

    public Date getOverrideTime() {
        return overrideTime;
    }

    public void setOverrideTime(Date overrideTime) {
        this.overrideTime = overrideTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
