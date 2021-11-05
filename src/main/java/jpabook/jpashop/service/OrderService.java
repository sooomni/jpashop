package jpabook.jpashop.service;

import org.springframework.stereotype.Service;

import java.util.List;

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
		Member member = memeberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);

		//배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());

		OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);

		//주문 생성
		Order.createOrder(member, delivery, orderItem);

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