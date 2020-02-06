package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    /**
     * 실무에서는 @ManyToMany 사용을 권장하지 않음. (단지 예시를 보여주기 위하여 사용.)
     * 예시에서 Category와 Item은 다대다 관계인데 이를 일대다, 다대일 관계로 풀어주기 위해
     * 중간에 매핑 테이블인 category_item를 만들어줌.
     * @JoinTable(name = "category_item") 설정으로 중간 테이블 설정.
     * (실무에서는 등록 날짜 등 다른 데이터들이 더 많기 때문에 매핑테이블로 해결이 안되는 경우가 많음.)
     */
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

}
