package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Catetory {
    @Id
    @GeneratedValue
    @Column(name = "catetory_id")
    private Long id;

    private String name;


    @ManyToMany
    @JoinTable(
            name = "catetory_item",
            joinColumns = @JoinColumn(name = "catetory_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Catetory parent;

    @OneToMany(mappedBy = "parent")
    private List<Catetory> child = new ArrayList<>();


    public void addChildCatetory(Catetory child) {
        this.child.add(child);
        child.setParent(this);
    }
}
