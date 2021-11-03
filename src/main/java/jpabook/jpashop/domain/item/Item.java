package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorColumn(name="DTYPE")
public abstract class Item{

	@Id 
    @GeneratedValue 
    @Column(name="item_id")
	private Long id;

	private String name;

	private int price;

	private int stockQuantity;
}