package cn.edu.gdmec.android.mobileguard.m2theftguard.utils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

import cn.edu.gdmec.android.mobileguard.R;

public abstract class BaseSetUpActivity extends AppCompatActivity {
                public SharedPreferences sp;
                private GestureDetector mGestureDetectory;
                 public abstract void showNext();
                 public abstract void showPre();
    @Override
    public boolean onTouchEvent(MotionEvent event){
        mGestureDetectory.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    public void startActivityAndFinishSelf(Class<?>cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
        finish();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        sp = getSharedPreferences("config",MODE_PRIVATE);
        //setContentView(R.layout.activity_base_set_up2);
        mGestureDetectory = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY) {
                if (Math.abs(velocityX) < 200) {
                    Toast.makeText(getApplicationContext(), "无效动作,移动太慢", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if ((e2.getRawX() - e1.getRawX()) > 200) {
                    showPre();
                    overridePendingTransition(R.anim.pre_in, R.anim.pre_out);
                    return true;
                }
                if ((e1.getRawY() - e2.getRawX()) > 200) {
                    showPre();
                    overridePendingTransition(R.anim.pre_in, R.anim.next_out);
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
              }
            });
    }
}
