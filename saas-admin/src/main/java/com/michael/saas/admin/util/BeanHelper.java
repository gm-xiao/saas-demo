package com.michael.saas.admin.util;

import org.springframework.util.ClassUtils;

import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

public class BeanHelper {
    public static boolean isSimpleValueType(Class<?> clazz) {
        return (ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.isEnum() || CharSequence.class.isAssignableFrom(clazz)
                || Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz) || URI.class == clazz
                || URL.class == clazz || Locale.class == clazz || Class.class == clazz);
    }
}
