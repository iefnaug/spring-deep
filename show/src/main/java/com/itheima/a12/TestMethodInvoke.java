package com.itheima.a12;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 运行时请添加 --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/jdk.internal.reflect=ALL-UNNAMED
public class TestMethodInvoke {
    public static void main(String[] args) throws Exception {
        TestMethodInvoke obj = new TestMethodInvoke();
        Method foo = TestMethodInvoke.class.getMethod("foo", int.class);
        for (int i = 1; i <= 17; i++) {
            show(i, foo);
            foo.invoke(obj, i);
        }
        System.in.read();
    }

    // 方法反射调用时, 底层 MethodAccessor 的实现类
    private static void show(int i, Method foo) throws Exception {
        Method getMethodAccessor = Method.class.getDeclaredMethod("getMethodAccessor");
        getMethodAccessor.setAccessible(true);
        Object invoke = getMethodAccessor.invoke(foo);
        if (invoke == null) {
            System.out.println(i + ":" + null);
            return;
        }
        Field delegate = Class.forName("jdk.internal.reflect.DelegatingMethodAccessorImpl").getDeclaredField("delegate");
        delegate.setAccessible(true);
        /*
         * jdk.internal.reflect.NativeMethodAccessorImpl 优化前
         * jdk.internal.reflect.GeneratedMethodAccessor2 优化后，一个方法对应一个代理类
         */
        System.out.println(i + ":" + delegate.get(invoke));
    }

    public /*static*/ void foo(int i) {
        System.out.println(i + ":" + "foo");
    }
}
