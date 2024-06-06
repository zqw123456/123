package org.examination.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品id
     * 1-苹果 | 2-草莓 | 3-芒果
     */
    private Long id;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 是否打折
     */
    private Boolean isDiscount;

    /**
     * 打折点数
     */
    private BigDecimal points;


    public BigDecimal getResPrice(){
        if(isDiscount){
            return this.price.multiply(points);
        }else {
            return this.price;
        }
    }
}
