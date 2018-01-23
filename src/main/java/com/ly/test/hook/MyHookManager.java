package com.ly.test.hook;

import com.ly.test.tool.Lg;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Host-0171 on 2018/1/23.
 */

public class MyHookManager
{
    static Object activityThreadInstance;
    static Object mInstrumentation;

    public static void init() throws Exception
    {
        Class<?> activityThread = Class.forName("android.app.ActivityThread");
        Method currentActivityThread = activityThread
                .getDeclaredMethod("currentActivityThread");
        activityThreadInstance = currentActivityThread.invoke(null);

        Lg.d(activityThreadInstance.toString());
        // 拿到原始的 mInstrumentation字段
        Field mInstrumentationField = activityThread.getDeclaredField("mInstrumentation");
        mInstrumentationField.setAccessible(true);
        mInstrumentation = mInstrumentationField.get(activityThreadInstance);
        Lg.d(mInstrumentation.toString());
    }

    public static void injectInstrumentation() throws NoSuchFieldException,
            IllegalAccessException, IllegalArgumentException
    {
        Lg.d(" start injectInstrumentation");
        Field field_instrumentation = activityThreadInstance.getClass()
                                                            .getDeclaredField("mInstrumentation");
        field_instrumentation.setAccessible(true);
        MyInstrumentation instrumentationHook = new MyInstrumentation(mInstrumentation);
        field_instrumentation.set(activityThreadInstance, instrumentationHook);
        Lg.d(instrumentationHook.toString());
    }
}
