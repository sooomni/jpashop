package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery{
	@Id 
    @GeneratedValue 
    @Column(name="delivery_id")
	private Long id;

	@OneToOne(mappedby="delivery")
	private Order order;

	@Embedded
	private Address address;

	@Enumerated(EnumType.String)
	priavte DeliveryStatus status;

}