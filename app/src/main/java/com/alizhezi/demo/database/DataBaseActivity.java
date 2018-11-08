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

    BaseDaoFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        factory = BaseDaoFactory.getInstance();
    }

    public void createTable(View view){

        factory = BaseDaoFactory.getInstance();
    }

    public void  insertValue(View view){

        BaseDao<User> baseDao = factory.getBaseDao(User.class);

        long result = baseDao.insert(new User(1, "huihui", "6116"));

        Toast.makeText(this,result>0?"插入成功":"插入失败",Toast.LENGTH_LONG).show();
    }

    public void updateValue(View view){

    }

    public void deleteValue(View view){

    }

    public void queryValue(View view){

    }
}
