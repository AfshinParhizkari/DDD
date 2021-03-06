package com.afshin.finance.domain.entity;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 06
 * @Time 4:26 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: Value Object
 */

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Preorder implements Serializable {
    private Integer customerfk;
    private Integer productfk;
    private Integer quantity;
    private BigDecimal price;
}