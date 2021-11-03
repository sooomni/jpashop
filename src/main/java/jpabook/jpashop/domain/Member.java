package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
public class Member
{   
    @Id 
    @GeneratedValue 
    @Column(name="mamber_id")
    private Long id;
    
    private String name;
    
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> oreders = new ArrayList<>();
    
}