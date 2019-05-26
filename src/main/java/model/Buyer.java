package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BUYER", schema = "auction")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUYER_ID")
    private Integer buyerId;

    @Column(name = "NICKNAME", unique = true, length = 30, nullable = false)
    private String nickName;

    @Column(name = "MAIL", unique = true, length = 30, nullable = false)
    private String mail;

    @Column(name = "PASSWORD", length = 20, nullable = false)
    private String password;

    @Column(name = "PLACE", length = 40)
    private String place;

    @JsonIgnore
    @OneToMany(mappedBy = "thingId", cascade = CascadeType.ALL)
    private Set<Thing> thingsSet;

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Set<Thing> getThingsSet() {
        return thingsSet;
    }

    public void setThingsSet(Set<Thing> thingsSet) {
        this.thingsSet = thingsSet;
    }
}
