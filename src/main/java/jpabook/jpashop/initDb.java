package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class initDb {

    private  final  InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private  final EntityManager em;
        public void dbInit1(){
            Member member = new Member();
            member.setName("userA");
            member.setAddress(new Address("seoul","1","1111"));
            em.persist(member);

            Book book1 = createBook("JPA1 book", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2 book", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1,10000,1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2,20000,2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery,orderItem1,orderItem2);
            em.persist(order);
        }

        private Book createBook(String name, int stock, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(stock);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        public void dbInit2(){
            Member member = createMember("userB", "busan", "2", "2111");
            em.persist(member);

            Book book1 = createBook("Spring1 book", 20000, 200);
            em.persist(book1);

            Book book2 = createBook("Spring2 book", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1,20000,3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2,40000,4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery,orderItem1,orderItem2);
            em.persist(order);
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
    }
}