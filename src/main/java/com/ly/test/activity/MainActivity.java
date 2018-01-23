package com.ly.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ly.test.R;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testActivityStartActivity(View v)
    {
        startActivity(new Intent(this, TestActivity.class));
    }

    public void testContextStartActivity(View v)
    {
        Intent intent = new Intent(this, TestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    private void test()
    {

    }


}
