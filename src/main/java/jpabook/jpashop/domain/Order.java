package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order{
	
	@Id 
    @GeneratedValue 
    @Column(name="order_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Memmber member;

	@OneToMany(mappedby="order")
	private List<OrderItem> orderItems = new ArrayList<>();

	@OneToOne

	@JoinColumn(name="delivery_id")
	private Delivery delivery;

	private LocalDateTime orderDate;  //주문 시간
	
	@Enumerated(EnumType.String)
	private OrderStatus status;  //주문 상태 

}