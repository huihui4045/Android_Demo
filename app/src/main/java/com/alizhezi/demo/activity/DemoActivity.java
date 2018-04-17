package com.alizhezi.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alizhezi.demo.MainActivity;
import com.alizhezi.demo.R;
import com.alizhezi.demo.base.BaseActivity;
import com.squareup.picasso.Picasso;

public class DemoActivity extends BaseActivity {

    private ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        image = ((ImageView) findViewById(R.id.imageView));

        String url = "http://mmp.mmxyz.net/show.php?src=http://mmp.mmxyz.net/images/2018/04/4030134971.jpg&w=285&zc=1";

        Picasso.with(this).load(url).into(image);
    }

    public void start(View view){

        startActivity(new Intent(this,BActivity.class));
    }

    public void startDemo(View view){
        startActivity(new Intent(this,DemoActivity.class));
    }

    public void startMain(View view){
        startActivity(new Intent(this,MainActivity.class));
    }
}
