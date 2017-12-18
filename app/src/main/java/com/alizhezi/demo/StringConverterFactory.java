package com.alizhezi.demo;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by gavin
 * Time 2017/12/18  16:53
 * Email:molu_clown@163.com
 */

public class StringConverterFactory extends Converter.Factory {


    public static StringConverterFactory create() {

        return new StringConverterFactory ();
    }



    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {


        return new StringResponseBodyConverter<>();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {


        Log.e("requestBodyConverter","发起请求");

        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    static class   StringResponseBodyConverter<T> implements Converter<ResponseBody, T>{

        @Override
        public T convert(ResponseBody value) throws IOException {



            Log.e("ResponseBodyConverter","StringResponseBodyConverter");


            return (T) value.string();
        }
    }

   static final class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
       // private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

       private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

        JsonRequestBodyConverter() {

        }

        public RequestBody convert(T value) throws IOException {


            String content = value.toString();

            Log.e("RequestBodyConverter","content:"+content);

            return RequestBody.create(MEDIA_TYPE, content);
        }
    }
}
