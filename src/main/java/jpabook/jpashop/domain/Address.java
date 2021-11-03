package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address{

	private String city;
	private String street;
	private String zipcode;

	//JPA 스펙상 엔티티나 임베디드 타입은 자바 기본 생성자를 public or protected로 설정해야 한다
	protected Address(){}

	//값 타입은 변경 불가능하게 생성해야 한다
	//생성자에서 값을 모두 초기화해서 변경 불가능한 클래스를 만들자
	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
}