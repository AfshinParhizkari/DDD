package com.afshin.finance.infrastructure.mq;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 09
 * @Time 10:09 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
import com.afshin.finance.domain.entity.Product;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductMq {
    @Autowired private RabbitTemplate rabbitTemplate;
    @Autowired private Binding binding;
    @Value("${product.routingkey}") String routingKey;

    public Integer sendProductQuantity(List<Product> products){
        try {
            rabbitTemplate.convertAndSend(binding.getExchange(),routingKey,products);
            return 0;
        }catch (Exception ex){return 1;}
    }
}
