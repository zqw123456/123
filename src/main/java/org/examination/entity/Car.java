package org.examination.entity;

import lombok.Data;

import java.util.HashMap;

@Data
public class Car {

    /**
     * 商品id和数量映射
     */
    HashMap<Long,Integer> totalCommodity;

    /**
     * 是否满减
     */
    private Boolean isFullMinus;

    /**
     * 满减信息 金额-减的额度
     */
    private String fullMinus;
}
