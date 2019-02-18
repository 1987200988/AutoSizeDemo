package com.baidu.isoft.autosizedemo;

import com.baidu.isoft.autosizedemo.autosize.AutoSizeUtils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoSizeUtils.getSingleton().setCustomDensity(getApplication(),this);

        setContentView(R.layout.activity_main);








    }
}
