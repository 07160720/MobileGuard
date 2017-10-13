package cn.edu.gdmec.android.mobileguard.m2theftguard.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

import cn.edu.gdmec.android.mobileguard.R;

public class Setup4Activity extends BaseSetUpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);
        ((RadioButton) findViewById(R.id.rb_four)).setChecked(true);
    }
    public void showNext(){
        startActivityAndFinishSelf(LostFindActivity.class);
    }
    public void showPre(){
        startActivityAndFinishSelf(Setup3Activity.class);
    }
}
