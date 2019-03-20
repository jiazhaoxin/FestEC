package com.diabin.festec.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.diabin.latte.activities.ProxyActivity;
import com.diabin.latte.app.Latte;
import com.diabin.latte.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {
    @Override
    public LatteDelegate setRootDelegare() {
        return new ExampleDelagate();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Toast.makeText(Latte.getApplication(), "传入context了", Toast.LENGTH_SHORT).show();
//    }
}
