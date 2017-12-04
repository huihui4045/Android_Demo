package com.alizhezi.handlerdemo;

import android.os.AsyncTask;

/**
 * Created by gavin
 * Time 2017/12/4  14:40
 * Email:molu_clown@163.com
 *
 */

/****
 * @String
 * @Integer
 * @String
 */
public class DownLoadAsyncTask extends AsyncTask<String,Integer,String> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {


        publishProgress(1);

        return null;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
