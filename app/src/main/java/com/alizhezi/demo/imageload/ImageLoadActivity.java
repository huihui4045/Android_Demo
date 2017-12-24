package com.alizhezi.demo.imageload;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alizhezi.demo.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

public class ImageLoadActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;


    String imageUrl="https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1513866655&di=6c56b607b40415a989ee7450ee7c8e80&src=http://imgsrc.baidu.com/imgad/pic/item/728da9773912b31bc2fe74138d18367adab4e17e.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_picasso)
    public void loadImageFromPicasso(){
        /****
         * Picasso使用了流式接口的调用方式
           Picasso类是核心实现类。
           实现图片加载功能至少需要三个参数：
         */
        ///Context对于很多Android API的调用都是必须的
        Picasso.with(this)
        //   load(String imageUrl)：被加载图像的Url地址。
                .load(imageUrl)
                 .placeholder(R.color.colorAccent)//预加载占位
                .error(R.color.colorPrimary)//加载失败的颜色
                .transform(new CustomTransformer())//设置图片转化器
                //要加载的View
                .into(imageView);

       OkHttpClient okHttpClient=new OkHttpClient();

       Picasso picasso = new Picasso.Builder(this)
         .downloader(new OkHttp3Downloader(okHttpClient))
          .build();


    }

    /***
     * 设置图片转换器
     */
    private class CustomTransformer implements Transformation{

        @Override
        public Bitmap transform(Bitmap source) {

            // TODO: 2017/12/21
            return null;
        }

        @Override
        public String key() {
            return null;
        }
    }


    public void getView(int position, View convertView, ViewGroup parent) {
        ImageView view = (ImageView) convertView;
        if (view == null) {
            view = new ImageView(this);
        }


        Picasso.with(this).load(imageUrl).into(view);
    }


}
