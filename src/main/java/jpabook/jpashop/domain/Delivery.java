package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    /**
     * enum 타입 선언 시 @Enumerated 선언해줌.
     * (1) EnumType.ORDINAL => 컬럼이 숫자값으로 들어감. enum값들 중간에 다른 값이 추가되면 번호가 변경되기 때문에 문제가 생김. 사용x.
     * (2) EnumType.STRING => 꼭 EnumType.STRING으로 사용.
     */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // READY, COMP

}
