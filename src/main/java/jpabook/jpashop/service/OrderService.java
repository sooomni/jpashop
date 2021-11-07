package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.ItemRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	//주문
	@Transactional
	public Long order(Long memberId, Long itemId, int count){
		//엔티티 조회
		MemberRepository memeberRepository;
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);

		//배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());

		OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);

		//주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);

		//주문 저장
		orderRepository.save(order); //caseCase 조건 때문에 요 시점에 delivery, orderItem도 함께 persist 됨
		return order.getId();
	}

	//취소
	@Transactional
	public void cancelOrder(Long orderId){
		Order order = orderRepository.findOne(orderId);
		//주문 취소 
		order.cancel();
	}

	//검색
	/*public List<Order> findOrders(OrderSearch orderSearch){
	*	return orderRepository.findAll(orderSearch);
	* }
	*/
	
}