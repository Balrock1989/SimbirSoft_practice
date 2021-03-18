package simbirSoftPractice.demo.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "shop_with_items")
@NoArgsConstructor
@Getter
@Setter
public class ShopWithItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order")
    private Long number;

    @Column(name = "pay")
    private int pay;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "shop_name")
    private String shopName;
}
