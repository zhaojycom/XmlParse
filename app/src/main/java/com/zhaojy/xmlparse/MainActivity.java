package com.zhaojy.xmlparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Student> list = null;
        try {
            list = DomXml.dom2xml(getResources().getAssets().open("xml.xml"));
            Log.e(TAG, list.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
