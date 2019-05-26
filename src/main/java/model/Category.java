package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CATEGORY", schema = "auction")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Column(name = "CATEGORY_NAME", length = 30, unique = true)
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "thingId", cascade = CascadeType.ALL)
    private Set<Thing> things;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Thing> getThings() {
        return things;
    }

    public void setThings(Set<Thing> things) {
        this.things = things;
    }
}
