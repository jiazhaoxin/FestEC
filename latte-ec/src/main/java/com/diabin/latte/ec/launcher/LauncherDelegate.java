package com.diabin.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.util.timer.BaseTimerTask;
import com.diabin.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

/**
 * Created by admin on 2019/3/20.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {
//    @BindView(R2.id.tv_launcher_timer)

    AppCompatTextView tvTimer = null;

    private Timer timer = null;
    private int count = 5;

    public void onClickTimerView() {

    }

    private void initTimer() {
        timer = new Timer();
        final BaseTimerTask baseTimerTask = new BaseTimerTask(this);
        timer.schedule(baseTimerTask, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        tvTimer = (AppCompatTextView) this.getView().findViewById(R.id.tv_launcher_timer);
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tvTimer!=null){
                    tvTimer.setText(MessageFormat.format("跳过\n{0}s",count));
                    count--;
                    if (count<0){
                        if (timer!=null){
                            timer.cancel();
                            timer = null;
                        }
                    }
                }
            }
        });
    }
}
