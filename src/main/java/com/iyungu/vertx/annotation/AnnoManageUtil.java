package com.iyungu.vertx.annotation;

import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.*;

public final class AnnoManageUtil {

    /**
     * 获取指定文件下面的RequestMapping方法保存在list中
     *
     * @param packageName 搜索的包名
     * @return
     */
    public static Map<String, List<ExecutorBean>> getRequestMappingMethod(String packageName) throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(RestController.class);

        // 存放url和ExecutorBean的对应关系
        Map<String, List<ExecutorBean>> map = new HashMap<>();

        for (Class classes : classesList) {
            RestController annotation = (RestController) classes.getAnnotation(RestController.class);
            // 获取命名空间
            String nameSpace = annotation.value();
            //得到该类下面的所有方法
            Method[] methods = classes.getDeclaredMethods();
            List<ExecutorBean> list = new ArrayList<>();

            for (Method method : methods) {
                // 得到该类下面的RequestMapping注解
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if (null != requestMapping) {
                    ExecutorBean executorBean = new ExecutorBean();
                    executorBean.setCls(classes);
                    executorBean.setMethod(method);
                    executorBean.setNameSpace(nameSpace);
                    executorBean.setMethodType(requestMapping.methodType());
                    executorBean.setValue(requestMapping.value());
                    executorBean.setClassInstance(classes.newInstance());
                    list.add(executorBean);
                }
            }

            map.merge(nameSpace, list, ((oldList, newList) -> {
                oldList.addAll(newList);
                return oldList;
            }));

        }
        return map;
    }

}