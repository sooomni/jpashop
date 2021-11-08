package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired
	EntityManager em;
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	public void 상품주문() throws Exception{
		//given
		Member member = createMember();
 		Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
 		int orderCount = 10;

		//when
		Long orderId = orderService.order(member.getId(),book.getId(),orderCount);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		assertEquals("상품 주문 시 상태는 Order", OrderStatus.ORDER, getOrder.getStatus());
		assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
		assertEquals("주문 가격은 가격 * 수량이다.",10000 * 10, getOrder.getTotalPrice());
		assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8,item.getStockQuantity());
	}

	@Test
	public void 주문취소() throws Exception{
		//given
		Member member = createMember();
		Item book = createBook("시골 JPA", 10000, 10);
		
		int orderCount = 2;

		Long orderId = orderService.order(member.getId(), item.getId(),orderCount);

		//when
		orderService.cancel(orderId);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		assertEquals("주문 취소 시 상태는 cancel이다.",OrderStatus.CANCEL, getOrder.getStatus());		
		assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.",10,item.getStockQuantity());		
	}

	@Test(expected = NotEnoughStockException.class)
	public void 상품주문_재고수량초과() throws Exception{
		//given
		Member member = createMember();
		Item book = createBook("시골 JPA", 10000, 10);
		int orderCount = 11;

		//when
		orderService.order(member.getId(),item.getId(), orderCount);

		//then
		fail("재고 수량 부족 예외가 발생해야 한다.")		
	}
	private Member createMember(){
		 Member member = new Member();
		 member.setName("회원1");
		 member.setAddress(new Address("서울","강가","123-123"));
		 em.persist(member);
		 return member;
	}
	private Book createBook(String name, int price, int stockQuantity){
		 Item book = new Book();
		 book.setName(name);
		 book.setPrice(price);
		 book.setStockQuantity(stockQuantity);
		 return book;
	}
}