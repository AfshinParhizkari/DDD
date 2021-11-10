package com.afshin.product.infrastructure.repository;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date Apr 26, 2021 2:58:19 AM
 * @version
 * Created by Eclipse 2020-09
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: 
*/

import com.afshin.product.domain.entity.Product;
import com.afshin.product.domain.entity.Quantity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer>{
	Page<Product> findAll(Pageable pageable);
	List<Product> findByProductpk(Integer productcode);
	Page<Product> findByActive(Pageable pageable,Boolean isactive);
	@Query("SELECT p.productpk,p.quantity FROM Product p where p.productpk in :productkeys")
	List<Quantity> getProductQuantity(List<Integer> productkeys);
	List<Quantity> findByProductpkIn(List<Integer> productkeys);
}