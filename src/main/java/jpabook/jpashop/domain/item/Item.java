package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();

	//stock 증가
	public void addStock(int quantity){
		this.stockQuantity += quantity;
	}
	//stock 감소
	public void removeStock(int quantity){
		int restStock = this.stockQuantity - quantity;
		if(restStock < 0){
			throw new NotEnoughtStockException("need more stock");
		}
		this.stockQuantity = quantity;
	}
}