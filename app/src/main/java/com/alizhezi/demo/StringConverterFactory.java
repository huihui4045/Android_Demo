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


        return new StringResponseBodyConverter<UserBean>();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new JsonRequestBodyConverter<>();
    }






    static class   StringResponseBodyConverter<T> implements Converter<ResponseBody, T>{

        @Override
        public T convert(ResponseBody value) throws IOException {

            UserBean userBean=new UserBean();

            userBean.setAge(20);

            userBean.setName("灰灰");

            Log.e("ResponseBodyConverter","StringResponseBodyConverter");


            return (T) userBean;
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
