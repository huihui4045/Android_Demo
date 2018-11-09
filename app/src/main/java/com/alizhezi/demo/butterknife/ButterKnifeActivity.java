package com.alizhezi.demo.butterknife;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.alizhezi.demo.R;
import com.alizhezi.demo.base.BaseActivity;

public class ButterKnifeActivity extends BaseActivity {

    @BindView(R.id.text)
    Button mTest;

    @BindView(R.id.text1)
    Button mTest2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);

        ButterKnife.bind(this);

        mTest.setText("这个是测试");

        //mTest2=findViewById(R.id.text1);
    }

    @OnClick({R.id.text,R.id.text1})
    public void test(){

        Toast.makeText(this,"这个测试",Toast.LENGTH_LONG).show();
    }


}
