package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired
	EntityManger em;
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	public void 상품주문() throws Exception(){
		//given
		 Member member = new Member();
		 member.setName("회원1");
		 member.setAddress(new Address("서울","강가","123-123"));
		 em.persist(member);

		 Item book = new Book();
		 book.setName("시골 JPA");
		 book.setPrice(10000);
		 book.setStockQuantity(10);

		 int orderCount = 2;

		//when
		Long getOrder = orderService.order(member.getId(),book.getId(),orderCount);
		
		//then
		Assert.assrtEquals("상품 주문 시 상태는 Order",OrderStatus.ORDER, getOrder.getStatus());
	}

	@Test
	public void 주문취소() throws Exception(){
		//given
		
		//when
		
		//then		
	}

	@Test
	public void 상품주문_재고수량초과() throws Exception(){
		//given
		
		//when
		
		//then			
	}
}