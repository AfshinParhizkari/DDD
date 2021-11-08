package com.afshin.finance.infrastructure.mq;

import com.afshin.finance.domain.entity.Preorder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 08
 * @Time 8:43 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: connect to special queue and get the preorder
 */
@Service
public class OrderMq {
    @Autowired private RabbitTemplate rabbitTemplate;
    public List<Preorder> payInvoice(String customercode){
        return (List<Preorder>)rabbitTemplate.receiveAndConvert(customercode);
    }
}