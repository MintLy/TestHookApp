package com.ly.test.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.ly.test.tool.Lg;

import java.lang.reflect.Method;

/**
 * Created by Host-0171 on 2018/1/23.
 */

public class MyInstrumentation extends Instrumentation
{
    private Object mInstrumentation;

    public MyInstrumentation(Object pInstrumentation)
    {
        this.mInstrumentation = pInstrumentation;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options)
    {

        // Hook之前, XXX到此一游!
        Lg.d("\n执行了startActivity, 参数如下: \n" + "who = [" + who + "], " +
                "\ncontextThread = [" + contextThread + "], \ntoken = [" + token + "], " +
                "\ntarget = [" + target + "], \nintent = [" + intent +
                "], \nrequestCode = [" + requestCode + "], \noptions = [" + options + "]");

        // 开始调用原始的方法, 调不调用随你,但是不调用的话, 所有的startActivity都失效了.
        // 由于这个方法是隐藏的,因此需要使用反射调用;首先找到这个方法
        try
        {
            Method execStartActivity = Instrumentation.class.getDeclaredMethod(
                    "execStartActivity",
                    Context.class, IBinder.class, IBinder.class, Activity.class,
                    Intent.class, int.class, Bundle.class);
            execStartActivity.setAccessible(true);
            return (ActivityResult) execStartActivity.invoke(mInstrumentation, who,
                    contextThread, token, target, intent, requestCode, options);
        }
        catch (Exception e)
        {
            // 某该死的rom修改了  需要手动适配
            throw new RuntimeException("do not support!!! pls adapt it");
        }
    }
}
