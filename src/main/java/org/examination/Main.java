package org.examination;

import org.examination.entity.Car;
import org.examination.entity.Commodity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        HashMap<Long, Commodity> products = new HashMap();
        // 初始化商品信息
        Commodity apple = new Commodity("苹果",1L,new BigDecimal(8),false,null);
        Commodity strawberry = new Commodity("草莓",2L,new BigDecimal(13),false,null);
        Commodity mango = new Commodity("芒果",3L,new BigDecimal(20),false,null);

        products.put(apple.getId(), apple);
        products.put(strawberry.getId(), strawberry);

        System.out.println("商品id：苹果-1 8元/斤,草莓-2 13元/斤");
        System.out.println("请按格式输入购买的物品信息（商品Id1-斤数 商品Id2-斤数），示例：1-2 2-2");

        // 1、若干斤苹果 若干斤草莓
        Car car = getCar();
        if(!Objects.isNull(car.getTotalCommodity())){
            BigDecimal totalPrice = getTotalPrice(car, products);
            System.out.println("总金额为：" + totalPrice);
            System.out.println();
        }
        // 2、增加芒果 20元/斤
        System.out.println("新增水果：芒果 id-3 价格 20/斤");
        products.put(mango.getId(), mango);
        Car car1 = getCar();
        if(!Objects.isNull(car.getTotalCommodity())){
            BigDecimal totalPrice = getTotalPrice(car1, products);
            System.out.println("总金额为：" + totalPrice);
            System.out.println();
        }
        // 3、草莓打八折
        System.out.println("草莓打八折，10.4元/斤");
        Commodity strawberry1 = new Commodity("草莓",2L,new BigDecimal(13),true,new BigDecimal("0.8"));
        products.put(strawberry1.getId(), strawberry1);
        Car car3 = getCar();
        if(!Objects.isNull(car.getTotalCommodity())){
            BigDecimal totalPrice = getTotalPrice(car3, products);
            System.out.println("总金额为：" + totalPrice);
            System.out.println();
        }
        // 4、满减
        System.out.println("满减活动，满100减10");
        Car car4 = getCar();
        car4.setIsFullMinus(true);
        car4.setFullMinus("100-10");
        if(!Objects.isNull(car.getTotalCommodity())){
            BigDecimal totalPrice = getTotalPrice(car4, products);
            System.out.println("总金额为：" + totalPrice);
            System.out.println();
        }
    }

    private static Car getCar() {
        Scanner scanner = new Scanner(System.in);
        String res = scanner.nextLine();
        Car car = new Car();
        if(!res.isEmpty()){
            String[] split = res.split(" ");
            if (split.length != 0) {
                HashMap<Long,Integer> map = new HashMap<>();
                for (int i = 0; i < split.length; i++) {
                    String[] split1 = split[i].trim().split("-");
                    map.put(Long.parseLong(split1[0]), Integer.valueOf(split1[1]));
                }
                car.setTotalCommodity(map);
            }
        }else {
            System.out.println("输入的商品信息为空！！！");
        }
        return car;
    }

    private static BigDecimal getTotalPrice(Car car, HashMap<Long, Commodity> productInformation){
        // 总金额
        BigDecimal totalPrice = new BigDecimal(0);
        HashMap<Long, Integer> totalCommodity = car.getTotalCommodity();
        for (Long id : totalCommodity.keySet()) {
            if (!productInformation.keySet().contains(id)){
                continue;
            }
            BigDecimal currentMoney = productInformation.get(id).getResPrice().multiply(BigDecimal.valueOf(totalCommodity.get(id)));
            totalPrice = totalPrice.add(currentMoney);
        }
        // 满减计算
        if(!Objects.isNull(car.getIsFullMinus())){
            if(car.getIsFullMinus()){
                String[] split = car.getFullMinus().split("-");
                BigDecimal limit = BigDecimal.valueOf(Integer.parseInt(split[0]));
                BigDecimal subtract = BigDecimal.valueOf(Integer.parseInt(split[1]));
                if(totalPrice.compareTo(limit) > 0){
                    totalPrice = totalPrice.subtract(subtract);
                }
            }
        }
        return totalPrice;
    }
}