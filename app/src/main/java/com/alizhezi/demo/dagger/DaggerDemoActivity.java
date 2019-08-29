package com.alizhezi.demo.dagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alizhezi.demo.R;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaggerDemoActivity extends AppCompatActivity implements View.OnClickListener {


    @Inject
    Student student;

    @BindView(R.id.btn_click)
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dagger_demo);
        ButterKnife.bind(this);

        SimpleComponent component = DaggerSimpleComponent.create();

        component.inject(this);
        btnClick.setText(student.getName());
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_click:

                student.action();

                break;
        }
    }
}
