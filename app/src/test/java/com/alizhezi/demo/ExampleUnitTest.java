package com.alizhezi.demo;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

             @Test
    public void testRxJava2(){

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {

                emitter.onNext(1);

                System.out.println("subscribe ="+Thread.currentThread().getName());

            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        System.out.println("onSubscribe ="+Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Integer integer) {

                        System.out.println("onNext ="+integer.intValue()+"   "+Thread.currentThread().getName());


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    @Test
    public void  testRxJava(){

        Observable<Integer> observable=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {


                System.out.println("subscribe ");


                emitter.onNext(1);
                emitter.onNext(2);

                emitter.onNext(3);

                emitter.onComplete();

                emitter.onNext(4);

                System.out.println("emitter =");
            }
        });

        Observer<Integer> observer=new Observer<Integer>() {

            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {

                System.out.println("onSubscribe");
                this.disposable=d;
            }

            @Override
            public void onNext(Integer integer) {

                System.out.println("integer = [" + integer + "]");


            }

            @Override
            public void onError(Throwable e) {

                System.out.println("e = [" + e.getMessage() + "]");
            }

            @Override
            public void onComplete() {

                System.out.println("onComplete");
            }
        };

        observable.subscribe(observer);
    }
}