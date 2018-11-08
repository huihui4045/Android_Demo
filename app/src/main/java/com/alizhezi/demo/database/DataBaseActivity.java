package com.alizhezi.demo.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alizhezi.demo.R;
import com.alizhezi.demo.database.bean.User;
import com.alizhezi.demo.database.db.BaseDao;
import com.alizhezi.demo.database.db.BaseDaoFactory;

public class DataBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
    }

    public void createTable(View view){


        BaseDaoFactory factory = BaseDaoFactory.getInstance();

        BaseDao<User> baseDao = factory.getBaseDao(User.class);

        Toast.makeText(this,"创建成功",Toast.LENGTH_LONG).show();

    }
}
