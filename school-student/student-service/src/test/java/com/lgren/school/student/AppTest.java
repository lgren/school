package com.lgren.school.student;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        Map<String, String> map = new HashMap<>(0);
        // java8 方式
        map.forEach((k,v) -> {
            System.out.println(k + v);
        });

        // 一般方式
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }

        // 不推荐方式 相当于会遍历两次
        for (String str : map.keySet()) {
            System.out.println(str + map.get(str));
        }
    }
}
