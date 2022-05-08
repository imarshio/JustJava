package CodeTest;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author masuo
 * @date: 2022/05/08/ 上午10:01
 * @description
 */
public class AlephTest {

    // "Order{ long id; String shop; BigDecimal value};
    // List<Order> orderList;有下列内容：
    // {1,""shopA"",10},{2,""shopB"",10},{3,""shopB"",20},{4,""shopC"",10},{5,""shopC"",20},{6,""shopC"",30}，{7,""shopC"",null}.
    //  1)对orderList 按shop分组；
    //  2)求各个shop的value之和；
    //  3)求各个shop的value的平均值，保留2位小数
    //  4)求各个shop的value最大、最小值
    //考虑真实情况下，可能有的order的属性是null，这种order不作处理，
    //尽量使用最简的运算复杂度，循环遍历越少越好"

    public static void main(String[] args) {

        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, "shopA", new BigDecimal(10)));
        orderList.add(new Order(2, "shopB", new BigDecimal(10)));
        orderList.add(new Order(3, "shopB", new BigDecimal(20)));
        orderList.add(new Order(4, "shopC", new BigDecimal(10)));
        orderList.add(new Order(5, "shopC", new BigDecimal(20)));
        orderList.add(new Order(6, "shopC", new BigDecimal(30)));
        orderList.add(new Order(7, "shopC", null));

        HashMap<String, List<Order>> orderGroup = new HashMap<>();

        // 分组
        orderList.forEach(order -> {
            if (order.getValue() == null) {
                return;
            }
            String shop = order.getShop();
            if (orderGroup.containsKey(shop)) {
                orderGroup.get(shop)
                        .add(order);
            } else {
                List<Order> orders = new ArrayList<>();
                orders.add(order);
                orderGroup.put(shop, orders);
            }
        });

        // 求和
        HashMap<String, BigDecimal> sumOfOrderGroup = new HashMap<>();
        HashMap<String, Double> avgOfOrderGroup = new HashMap<>();
        HashMap<String, Double> maxOfOrderGroup = new HashMap<>();
        HashMap<String, Double> minOfOrderGroup = new HashMap<>();


        orderGroup.forEach((key, orderGroupList) -> {

            Iterator<Order> iterator = orderGroupList.iterator();
            Double max = Double.MIN_VALUE;
            Double min = Double.MAX_VALUE;
            while (iterator.hasNext()) {
                Order next = iterator.next();
                max = Math.max(next.getValue().doubleValue(), max);
                min = Math.min(next.getValue().doubleValue(), min);

            }

            BigDecimal sum = orderGroupList.stream().map(Order::getValue)
                    .reduce(BigDecimal::add)
                    .get();

            sumOfOrderGroup.put(key, sum);
            Double avg = orderGroupList.stream().map(Order::getValue).mapToDouble(BigDecimal::doubleValue).average().getAsDouble();

            avgOfOrderGroup.put(key, avg);

            maxOfOrderGroup.put(key, max);
            minOfOrderGroup.put(key, min);
        });


        System.out.println("和");
        sumOfOrderGroup.forEach((key, sum) -> {
            System.out.println(key + " " + sum);
        });

        System.out.println("max");
        maxOfOrderGroup.forEach((key, max) -> {
            System.out.println(key + " " + max);
        });

        System.out.println("min");
        minOfOrderGroup.forEach((key, min) -> {
            System.out.println(key + " " + min);
        });

        System.out.println("avg");
        avgOfOrderGroup.forEach((key, avg) -> {
            BigDecimal bd = new BigDecimal(avg);

            System.out.println(key + " " + new DecimalFormat("0.00").format(bd));
        });
    }


    static class Order {
        long id;
        String shop;
        BigDecimal value;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getShop() {
            return shop;
        }

        public void setShop(String shop) {
            this.shop = shop;
        }

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }

        public Order(long id, String shop, BigDecimal value) {
            this.id = id;
            this.shop = shop;
            this.value = value;
        }
    }

}
