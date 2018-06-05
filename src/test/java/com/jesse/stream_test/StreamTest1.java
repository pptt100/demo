package com.jesse.stream_test;

import com.google.common.collect.Lists;
import com.jesse.stream.OrdersDO;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Jesse on 2018/6/4.
 */
public class StreamTest1 {

    @Test
    public void test01() {
        List<OrdersDO> list = new ArrayList<>();//查询昨天一天的所有交易
        OrdersDO o1 = new OrdersDO();
        o1.setAppId(1L);
        o1.setTradeAmount(100L);
        o1.setStatus(1);
        list.add(o1);

        OrdersDO o2 = new OrdersDO();
        o2.setAppId(5L);
        o2.setTradeAmount(300L);
        o2.setStatus(2);

        list.add(o2);
        OrdersDO o3 = new OrdersDO();
        o3.setAppId(1L);
        o3.setTradeAmount(100L);
        o3.setStatus(3);
        list.add(o3);
        OrdersDO o4 = new OrdersDO();
        o4.setAppId(5L);
        o4.setTradeAmount(300L);
        o4.setStatus(4);
        list.add(o4);
        OrdersDO o5 = new OrdersDO();
        o5.setAppId(5L);
        o5.setTradeAmount(300L);
        o5.setStatus(4);
        list.add(o5);

        //统计每个应用实际支付总额
        Map<Long, Long> collect = list.stream().filter(p -> p.getStatus() == 2)
                .collect(Collectors.groupingBy(OrdersDO::getAppId,
                        Collectors.summingLong(OrdersDO::getTradeAmount)));
        System.out.println(collect);
        //统计每个应用取消总额
        Map<Long, Long> collect1 = list.stream().collect(Collectors.groupingBy(OrdersDO::getAppId,
                Collectors.summingLong(OrdersDO::getTradeAmount)));
        System.out.println(collect1);

        //统计每个应用下交易笔数

        Map<Long, Long> collect2 = list.stream().collect(Collectors.groupingBy(OrdersDO::getAppId,
                Collectors.counting()));
        System.out.println(collect2);
        //统计每个应用每种状态下交易笔数
        Map<Long, Map<Integer, Long>> collect3 = list.stream().collect(Collectors.groupingBy(OrdersDO::getAppId,
                Collectors.groupingBy(OrdersDO::getStatus, Collectors.counting())));
        System.out.println(collect3);
        //每个应用下交易笔数按数量排序
        Map<Long,Long> finalMap = new LinkedHashMap<>();
        collect2.entrySet().stream().sorted(Map.Entry.<Long, Long>comparingByValue().
                reversed()).forEachOrdered(e -> finalMap.put(e.getKey(),e.getValue()));
        System.out.println(finalMap);


        /*
            {5=300}
            {1=200, 5=900}
            {1=2, 5=3}
            {1={1=1, 3=1}, 5={2=1, 4=2}}
            {5=3, 1=2}
        */
    }

    @Test
    public final void givenList_whenParitioningIntoNSublistsUsingGroupingBy_thenCorrect() {
        /*List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 11);

        Map<Integer, List<Integer>> groups =
                intList.stream().collect(Collectors.groupingBy(s -> (s - 1) / 3));
        List<List<Integer>> subSets = new ArrayList<List<Integer>>(groups.values());*/

        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 11);
        List<List<Integer>> subSets = Lists.partition(intList, 3);

        System.out.println(subSets);

        /*List<Integer> lastPartition = subSets.get(2);
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);
        assertThat(subSets.size(), equalTo(3));
        assertThat(lastPartition, equalTo(expectedLastPartition));*/
    }

}
