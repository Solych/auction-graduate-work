package model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "THING", schema = "auction")
public class Thing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "THING_ID")
    private Integer thingId;

    @Column(name = "NAME", length = 30, nullable = false)
    private String name;

    @Column(name = "PICTURE", columnDefinition = "blob")
    @Lob
    private byte[] picture;

    @Column(name = "MIN_PRICE", nullable = false)
    private Integer minPrice;

    @Column(name = "DATE_OF_PUT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfPut;

    @Column(name = "TIME_FOR_SELLING", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sellingTime;

    @JoinColumn(name = "OWNER_ID", nullable = false)
    @ManyToOne
    private Buyer owner;

    @JoinColumn(name = "CATEGORY_ID")
    @ManyToOne
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getThingId() {
        return thingId;
    }

    public void setThingId(Integer thingId) {
        this.thingId = thingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Date getDateOfPut() {
        return dateOfPut;
    }

    public void setDateOfPut(Date dateOfPut) {
        this.dateOfPut = dateOfPut;
    }

    public Date getSellingTime() {
        return sellingTime;
    }

    public void setSellingTime(Date sellingTime) {
        this.sellingTime = sellingTime;
    }

    public Buyer getOwner() {
        return owner;
    }

    public void setOwner(Buyer owner) {
        this.owner = owner;
    }
}
