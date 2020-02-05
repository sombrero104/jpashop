package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Inheritance 전략 종류
 * (1) InheritanceType.JOINED
 *      JOINED 방식은 no, name, price 속성을 갖는 Item이라는 테이블을 생성하여
 *      Movie , Music , Book 테이블이 Item의 PK를 외래키로 갖는 방식입니다.
 *      정규화가 된 모델링을 사용하기 때문에 데이터의 중복이 없으므로 가장 많이 사용되는 방법입니다.
 * (2) InheritanceType.SINGLE_TABLE
 *      SINGLE_TABLE 방식은 Movie , Music , Book 각 테이블의 속성들을 Item 테이블의 속성으로 합치는 방식입니다.
 *      이름 그대로 하나의 테이블로 처리하겠다는거죠.
 * (3) InheritanceType.TABLE_PER_CLASS
 *      TABLE_PER_CLASS 방식은 부모의 속성들을 Movie , Music , Book 테이블의 속성으로 갖는 방식입니다.
 *      이 경우 join을 사용하지 않고 union을 사용하는데, union 쿼리는 사용하지 않는 것이 좋으므로 이 전략은 잘 사용하지 않습니다.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") // 싱글테이블 내에서 Movie , Music , Book을 구분하기 위한 값.
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
