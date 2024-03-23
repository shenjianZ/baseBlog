package com.aisi.utils;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/23 10:43
 * @Description:
 */


public class BeanCopyUtils {
    private BeanCopyUtils() {}

    @SneakyThrows
    public static <V> V  copyBean(Object source, Class<V> target) {
        V result = null;
        result = target.newInstance();
        BeanUtils.copyProperties(source, result);
        return result;
    }
    public static <O,V> List<V>  copyBeanList(List<O> source, Class<V> target) {
//        List<V> result = null;
//        result = new ArrayList<>();
//        for (Object obj : source) {
//            V v = copyBean(obj, target);
//            result.add(v);
//        }
       return source.stream().map(
                o -> copyBean(o, target))
                .collect(Collectors.toList());
    }

}
