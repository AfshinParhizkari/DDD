package com.afshin.shopping.infrastructure.repository;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date Apr 26, 2021 2:58:19 AM
 * @version
 * Created by Eclipse 2020-09
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: 
*/

import com.afshin.shopping.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartDao extends JpaRepository<Cart, Integer>{
    List<Cart> findByCartpk(Integer categorycode);
    List<Cart> findByCustomerfk(Integer customercode);

    @Modifying
    @Transactional
    @Query("delete from Cart c where c.customerfk = :customercode ")
    Integer deleteByCustomer(Integer customercode);

}