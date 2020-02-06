package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /**
     * cascade = CascadeType.ALL 옵션은
     * persist(oderItemA); persist(orderItemB); persist(orderItemC); persist(order);
     * 처럼 orderItem을 따로 저장하지 않고
     * persist(order);
     * 만 입력해도 orderItem도 다 같이 저장해 준다.
     * ALL옵션은 삭제시에도 같이 삭제해 준다.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

}
