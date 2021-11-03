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
	private Member member;

	@OneToMany(mappedBy="order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	/*
	* 	em.persist(order); -> order만 저장해도 아래 두 개 같이 저장
	*   em.persist(orderItemA);
	*   em.persist(orderItemC);
	*/

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="delivery_id")
	private Delivery delivery;

	private LocalDateTime orderDate;  //주문 시간
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;  //주문 상태

	//연관 관계 메서드
	public void setMember(Member member){
		this.member = member;
		member.getOreders().add(this);
	}

	public void addOrderItem(OrderItem orderItem){
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	public void setDelivery(Delivery delivery){
		this.delivery = delivery;
		delivery.setOrder(this);
	}
}