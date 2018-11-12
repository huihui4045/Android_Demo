package com.alizhezi.demo.database;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.alizhezi.demo.R;
import com.alizhezi.demo.base.BaseActivity;
import com.alizhezi.demo.database.bean.User;
import com.alizhezi.demo.database.db.BaseDao;
import com.alizhezi.demo.database.db.BaseDaoFactory;
import com.alizhezi.demo.database.db.BaseDaoNewImpl;

import java.util.List;

public class DataBaseActivity extends BaseActivity {

    BaseDaoFactory factory;

    private static int id;

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

        long result = baseDao.insert(new User(id++, "huihui"+id, "6116"));

        Toast.makeText(this,result>0?"插入成功":"插入失败",Toast.LENGTH_LONG).show();
    }

    public void updateValue(View view){


        BaseDao<User> baseDao = factory.getBaseDao(User.class);

        User user=new User();
        user.setPassword("0000");

        User where=new User();

        where.setId(1);
        where.setName("huihui2");

        baseDao.update(user,where);
    }

    public void deleteValue(View view){

    }

    public void queryValue(View view){

        BaseDao<User> baseDao = factory.getBaseDao(BaseDaoNewImpl.class,User.class);
        
        User where=new User();

        where.setPassword("6116");

        List<User> users = baseDao.query(where);

        for (User user : users) {

            Log.e(TAG,user.toString());
        }

    }
}
