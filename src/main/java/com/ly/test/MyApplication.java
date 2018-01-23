package com.ly.test;

import android.app.Application;

import com.ly.test.hook.MyHookManager;
import com.ly.test.tool.Lg;

/**
 * Created by Host-0171 on 2018/1/23.
 */

public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        try
        {
            Lg.d(" onCreate starting init");
            MyHookManager.init();
            MyHookManager.injectInstrumentation();
        }
        catch (Exception e)
        {
            Lg.e(" onCreate e:" + e.toString());
        }
        super.onCreate();
    }
}
