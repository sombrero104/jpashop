package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * Address는 내장타입(어딘가에 내장될 수 있는 타입.)이기 때문에 @Embeddable 선언.
 * Setter를 제공하지 않고 생성할 때에만 값을 세팅할 수 있도록 만듬.
 */
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    /**
     * JPA에서 프록시나 리플렉션을 많이 쓰는데
     * 기본 생성자가 없으면 사용을 못하기 때문에 기본 생성자를 만들어줌.
     * JPA 스펙에서는 접근을 protected까지 정의하는 것을 허용.
     */
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
