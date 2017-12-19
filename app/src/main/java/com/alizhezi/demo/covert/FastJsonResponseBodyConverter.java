package com.alizhezi.demo.covert;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

/**
 * Created by molu_ on 2017/12/19.
 */

public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Type type;

    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();
        Log.e("ResponseBodyConverter",tempStr);

        tempStr=" {\n" +
                "                                                                            mts:'1851447',\n" +
                "                                                                            province:'����',\n" +
                "                                                                            catName:'�й�jͨ',\n" +
                "                                                                            telString:'18514476718',\n" +
                "                                                                        \tareaVid:'29400',\n" +
                "                                                                        \tispVid:'137815084',\n" +
                "                                                                        \tcarrier:'����jͨ'\n" +
                "                                                                        }";
        return JSON.parseObject(tempStr, type);

    }
}