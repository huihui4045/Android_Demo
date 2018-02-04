package com.alizhezi.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.alizhezi.demo.R;
import com.alizhezi.demo.base.BaseActivity;

public class FragmentDemoActivity extends BaseActivity implements View.OnClickListener {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private String [] fragmentNames={"fragmentA","fragmentB"};

    private AFragment mAFragment;

    private BFragment mBFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);

        findViewById(R.id.btn_a).setOnClickListener(this);
        findViewById(R.id.btn_b).setOnClickListener(this);

        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();

       /* if (mAFragment==null){

            mAFragment=new AFragment();


            transaction.add(R.id.fragment_content,mAFragment,fragmentNames[0]);

            Log.e("mAFragment:","mAFragment is null");


        }

        transaction.commit();*/

    }

    @Override
    public void onClick(View v) {

        transaction = manager.beginTransaction();

        switch (v.getId()){



            case R.id.btn_a:

                hideAllFrags(transaction);

                if (mAFragment==null){

                    mAFragment=new AFragment();


                    transaction.replace(R.id.fragment_content,mAFragment,fragmentNames[0]);

                    transaction.addToBackStack(fragmentNames[0]);

                    Log.e("mAFragment:","mAFragment is null");


                }else {

                   // mAFragment= (AFragment) manager.findFragmentByTag(fragmentNames[0]);

                    //transaction.add(R.id.fragment_content,mAFragment,fragmentNames[0]);

                   // transaction.show(mAFragment);
                    transaction.add(R.id.fragment_content,mAFragment,fragmentNames[0]);
                }

            break;

            case R.id.btn_b:

                hideAllFrags(transaction);

                if (mBFragment==null){

                    mBFragment=new BFragment();

                    transaction.replace(R.id.fragment_content,mBFragment,fragmentNames[1]);

                    transaction.addToBackStack(fragmentNames[1]);

                    Log.e("mBFragment:","mBFragment is null");
                }else {

                   // mBFragment= (BFragment) manager.findFragmentByTag(fragmentNames[1]);

                    transaction.show(mBFragment);
                    //transaction.replace(R.id.fragment_content,mBFragment,fragmentNames[1]);

                    //transaction.add(R.id.fragment_content,mBFragment,fragmentNames[1]);
                }

                break;



        }

        transaction.commit();
    }

    private void hideAllFrags(FragmentTransaction transaction) {

        if (mAFragment!=null) {

            transaction.hide(mAFragment);



        }

        if (mBFragment != null) {

            transaction.hide(mAFragment);
        }
    }


}
