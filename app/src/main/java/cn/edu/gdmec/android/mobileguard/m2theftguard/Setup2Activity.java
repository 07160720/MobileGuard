package cn.edu.gdmec.android.mobileguard.m2theftguard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import cn.edu.gdmec.android.mobileguard.R;

public class Setup2Activity extends BaseSetUpActivity implements View.OnClickListener{
      private TelephonyManager mTelephonyManager;
      private Button mBindSIMBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup2);
        //设置第2个小圆点的颜色
        ((RadioButton) findViewById(R.id.rb_second)).setChecked(true);
        //获取电话管理器这个系统服务
        mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        mBindSIMBtn = (Button) findViewById(R.id.btn_bind_sim);
        mBindSIMBtn.setOnClickListener(this);
        if (isBind()){
            mBindSIMBtn.setEnabled(false);
        }else{
            mBindSIMBtn.setEnabled(true);
        }
    }
    private boolean isBind() {
        String simString = sp.getString("sim",null);
        if (TextUtils.isEmpty(simString)){
            return false;
        }
        return true;
    }
    public void showNext(){
        if (!isBind()){
            Toast.makeText(this,"你还没有绑定SIM卡！",Toast.LENGTH_LONG).show();
            return;
        }
        startActivityAndFinishSelf(Setup3Activity.class);
    }
   public  void showPre(){
        startActivityAndFinishSelf(Setup1Activity.class);
   }

   public void onClick(View view){
       switch (view.getId()){
           case R.id.btn_bind_sim:
               bindSIM();
               break;
       }
   }
   private void bindSIM(){
       if (!isBind()){
           String simSerialNumber = mTelephonyManager.getSimSerialNumber();
           SharedPreferences.Editor edit = sp.edit();
           edit.putString("sim",simSerialNumber);
           edit.commit();
           Toast.makeText(this, "SIM卡绑定成功！", Toast.LENGTH_LONG).show();
           mBindSIMBtn.setEnabled(false);
       }else{
           Toast.makeText(this,"SIM卡已经绑定！",Toast.LENGTH_LONG).show();
           mBindSIMBtn.setEnabled(false);
       }
   }
}
