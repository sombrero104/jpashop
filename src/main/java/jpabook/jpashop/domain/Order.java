package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    /**
     * Member와 양방향 연관관계
     * 연관관계의 주인: 연관관계에 있는 두 엔티티가 있을 때, 외래키가 변경될 경우,
     *  어떤 엔티티의 값을 기준으로 변경할 것인지에 대해 기준이 되는 엔티티를 연관관계의 주인이라고 한다.
     */
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
