package com.afshin.finance.domain.service;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 08
 * @Time 8:24 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: Logic of payment and close the buy
 */
import com.afshin.finance.domain.entity.*;
import com.afshin.finance.infrastructure.mq.OrderMq;
import com.afshin.finance.infrastructure.mq.ProductMq;
import com.afshin.finance.infrastructure.repository.OrderDao;
import com.afshin.finance.infrastructure.repository.OrderitemDao;
import com.afshin.finance.infrastructure.repository.PaymentDao;
import com.afshin.finance.infrastructure.resource.ProductRso;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentSrv {
    @Autowired private OrderMq orderMq;
    @Autowired private ProductMq productMq;
    @Autowired private OrderDao oDao;
    @Autowired private OrderitemDao dDao;
    @Autowired private PaymentDao pDao;
    @Autowired private ProductRso pRso;

    @Transactional(rollbackFor=Exception.class, propagation= Propagation.REQUIRED)
    public String payOrder(Integer customerCode, Timestamp transportDate,String transaction) throws IOException {
        List<Preorder> preorders= orderMq.payInvoice(customerCode.toString());
        if(preorders==null || preorders.size()==0) return "{'code':1,'message':'there aren't any product in your cart'}";
        Order order = oDao.save(new Order(customerCode,transportDate,"payed"));

        List<Orderitem> orderitems=new ArrayList<>();
        List<Product> products=new ArrayList<>();
        BigDecimal total= new BigDecimal(0);
        for(Preorder preo:preorders) {
            orderitems.add(new Orderitem(order.getOrderpk(), preo.getProductfk(), preo.getQuantity(), preo.getPrice()));
            total=total.add(preo.getPrice());
            products.add(new Product(preo.getProductfk(),preo.getQuantity()));
        }
        //Have you enough goods in Inventory?
        List<Integer> productKeys= products.stream().map(Product::getProductpk).collect(Collectors.toList());
        List<Product> vacantProduct=new ArrayList<>();
        List<Product> quantityList = pRso.getQuantity(productKeys);
        for(Product product:products){
            Integer dbQuantity=quantityList.stream().filter(entry -> entry.getProductpk()==product.getProductpk()).collect(Collectors.toList()).get(0).getQuantity();
            if(dbQuantity<product.getQuantity())
                vacantProduct.add(new Product(product.getProductpk(),product.getQuantity()));
        }
        if(vacantProduct.size()==0) {
            dDao.saveAll(orderitems);
            Payment payment = pDao.save(new Payment(order.getOrderpk(), total, transaction));
            productMq.sendProductQuantity(products);//remove quantity from Product app by mq
            return "{'order':'" + order.getOrderpk() + ",'payment':'" + payment.getPaymentpk() + "'}";
        }else {
            oDao.deleteById(order.getOrderpk());
            //send toward mq for returning to shopping app!
            return (new ObjectMapper()).writeValueAsString(vacantProduct);
        }
    }
}