package com.alizhezi.demo.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.alizhezi.demo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DemoAdapter extends BaseAdapter {

    private List<String> datas;

    private Context mContext;

    public DemoAdapter(List<String> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item, null, false);

            mViewHolder = new ViewHolder();

            mViewHolder.imageView = convertView.findViewById(R.id.imageView);

            convertView.setTag(mViewHolder);
        } else {

            mViewHolder = (ViewHolder) convertView.getTag();
        }

        String s = datas.get(position);

        // String url="http://mmp.mmxyz.net/show.php?src=http://mmp.mmxyz.net/images/2018/04/1580263130.jpg&w=285&zc=1";
        // String test="http://mmp.mmxyz.net/images/2018/04/498192135.jpg";
        Picasso.with(mContext).load(s).into(mViewHolder.imageView);


        return convertView;
    }

    class ViewHolder {

        ImageView imageView;


    }


}
