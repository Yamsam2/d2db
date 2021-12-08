package com.example.d2db;

import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

public abstract class OnSingleClickListener implements View.OnClickListener{

    //중복 클릭 방지 시간 설정 ( 해당 시간 이후에 다시 클릭 가능 )
    private static final long MIN_CLICK_INTERVAL = 3600000;
    // 이전에 클릭한 시간 변수
    private long mLastClickTime = 0;

    public abstract void onSingleClick(View v);

    @Override
    public final void onClick(View v) {
        // 현재 클릭한 시간 변수
        long currentClickTime = SystemClock.uptimeMillis();
        long elapsedTime = currentClickTime - mLastClickTime;
        mLastClickTime = currentClickTime;

        // 중복클릭 아닌 경우 (이전클리과 현재클릭의 시간차가 MIN_CLICK_INTERVAL 보다 큰 경우)
        if (elapsedTime >= MIN_CLICK_INTERVAL) {
            onSingleClick(v);
        }else {
            Toast.makeText(v.getContext(), "이미 평가하셨습니다. 한시간 뒤에 다시 평가해주세요.",Toast.LENGTH_SHORT).show();
        }
    }
}
