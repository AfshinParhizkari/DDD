package com.afshin.shoping.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 06
 * @Time 4:26 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */

@Entity
@Table(name = "cart")
public class Cart {
    private Integer cartpk;
    private Integer customerfk;
    private Integer productfk;
    private Integer quantity;
    private BigDecimal price;
    private Timestamp adddate;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) //specify the generation strategy used for the primary key.
    public Integer getCartpk() {return cartpk;}
    public void setCartpk(Integer cartpk) {this.cartpk = cartpk;}

	@Column(name = "customerfk")
    @NotNull(message = "Customer Code should not be empty")
    public Integer getCustomerfk() {return customerfk;}
    public void setCustomerfk(Integer customerfk) {this.customerfk = customerfk;}

	@Column(name = "productfk")
    @NotNull(message = "Product Code should not be empty")
    public Integer getProductfk() {return productfk;}
    public void setProductfk(Integer productfk) {this.productfk = productfk;}

	@Column(name = "quantity")
    @NotNull(message = "quantity should not be empty")
    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}

	@Column(name = "price")
    @NotNull(message = "price should not be empty")
    public BigDecimal getPrice() {return price;}
    public void setPrice(BigDecimal price) {this.price = price;}

	@Column(name = "adddate")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getAdddate() {return adddate;}
    public void setAdddate(Timestamp adddate) {this.adddate = adddate;}
}