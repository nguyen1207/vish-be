package com.nguyen1207.vishbe.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class MapUtil {
    public static void copyNonNullProperties(Object src, Object target, String... ignoreProperties) {
        String[] ignoreProps = Stream
                .concat(Arrays.stream(getNullPropertyNames(src)),
                        Arrays.stream(ignoreProperties))
                .toArray(String[]::new);

        BeanUtils.copyProperties(src, target, ignoreProps);
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
