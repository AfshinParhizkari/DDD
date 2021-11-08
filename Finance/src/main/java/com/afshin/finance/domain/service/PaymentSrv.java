package com.afshin.finance.domain.service;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 08
 * @Time 8:24 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
import com.afshin.finance.domain.entity.Order;
import com.afshin.finance.domain.entity.Orderitem;
import com.afshin.finance.domain.entity.Payment;
import com.afshin.finance.domain.entity.Preorder;
import com.afshin.finance.infrastructure.mq.OrderMq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentSrv {
    @Autowired private OrderMq resource;

    public String payOrder(Integer customerCode, Timestamp transportDate,String transaction){
        List<Preorder> preOrder= resource.payInvoice(customerCode.toString());
        Order order=new Order(customerCode,transportDate,"In Progress");
        //add
        List<Orderitem> orderitems=new ArrayList<>();
        BigDecimal total= new BigDecimal(0);
        for(Preorder po:preOrder) {
            orderitems.add(new Orderitem(order.getOrderpk(), po.getProductfk(), po.getQuantity(), po.getPrice()));
            total=total.add(po.getPrice());
        }
        //add
        Payment payment = new Payment(order.getOrderpk(),total,transaction);
        //add
        //product -- service? Mq?

        return "{'order':'"+order.getOrderpk()+",'payment':'"+payment.getPaymentpk()+"'}";
    }
}