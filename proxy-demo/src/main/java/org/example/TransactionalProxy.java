package org.example;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TransactionalProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Begin transaction");

        Object result = methodProxy.invokeSuper(o, objects);

        System.out.println("Commit transaction");
        return result;
    }
}
