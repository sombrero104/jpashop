package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * Address는 내장타입(어딘가에 내장될 수 있는 타입.)이기 때문에 @Embeddable 선언.
 */
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

}
