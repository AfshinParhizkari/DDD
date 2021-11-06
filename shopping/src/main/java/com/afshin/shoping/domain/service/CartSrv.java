package com.afshin.shoping.domain.service;

import com.afshin.shoping.domain.entity.Cart;
import com.afshin.shoping.infrastructure.repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 02
 * @Time 4:29 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
@Service
public class CartSrv {
    @Autowired private CartDao cartDao;

    public List<Cart> showCart() throws Exception {
        return cartDao.findAll();
    }
    public String deleteFromCart(Integer code) throws Exception {
        cartDao.deleteById(code);
        return "{'code':1,'message':'record code "+code+" is deleted'}";
    }
    public String cancelCart(Integer code) throws Exception {
        cartDao.deleteByCustomer(code);
        return "{'code':1,'message':'record code "+code+" is deleted'}";
    }
    public String addToCart(Cart cart) throws Exception {
            return "{'code':1,'message':'record code "+cartDao.save(cart).getCartpk()+" is added'}";
     }

    @Transactional
    public String closeCart(Integer code) throws Exception {
        cartDao.deleteByCustomer(code);
        //rabbitMq
        return "{'code':1,'message':'record code "+code+" is deleted'}";
    }
}