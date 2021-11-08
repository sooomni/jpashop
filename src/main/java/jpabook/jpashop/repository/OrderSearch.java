package jpabook.jpashop.service;

Import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.setter;

@Getter
@Setter
public class OrderSearch{

	private String memberName;
	private OrderStatus orderStatus;

}