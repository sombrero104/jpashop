package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    /**
     * Order와 양방향 연관관계
     * Order가 연관관계의 주인이므로,
     * 연관관계의 주인이 아닌 Member에는 mappedBy를 선언해준다.
     * 나는 Order 테이블에 있는 member 필드에 의해서 매핑된 거울일뿐이라는 의미.
     * 읽기전용이 됨. 이곳에 값을 변경한다고 해서 Order의 외래키 값이 변경되지 않음.
     */
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
