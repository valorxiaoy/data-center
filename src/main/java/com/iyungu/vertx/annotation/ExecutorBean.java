package com.iyungu.vertx.annotation;
import java.lang.reflect.Method;

public class ExecutorBean {

    private Class cls;

    private Object classInstance;

    private Method method;

    private String nameSpace;

    private String value;

    private String methodType;

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public Object getClassInstance() {
        return classInstance;
    }

    public void setClassInstance(Object classInstance) {
        this.classInstance = classInstance;
    }
}