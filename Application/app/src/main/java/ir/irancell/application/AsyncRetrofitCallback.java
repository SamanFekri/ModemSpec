package ir.irancell.application;
/**
 * Created by SKings (samanf74@gmail.com) on 8/8/2017.
 */

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import retrofit2.Call;
import retrofit2.Response;

import static java.lang.Thread.currentThread;

public abstract class AsyncRetrofitCallback<T> implements retrofit2.Callback<T> {

    private final Object lock;
    private Context context;

    public AsyncRetrofitCallback(Context context) {
        this.context = context;
        lock = new Object();
    }

    @Override
    public void onResponse(final Call<T> call, final Response<T> response) {
        if (currentThread() != context.getMainLooper().getThread()) {
            Handler mainHandler = new Handler(context.getMainLooper());

            Runnable preRunnable = new Runnable() {
                @Override
                public void run() {
                    synchronized (lock) {
                        onPreResponse(call, response);
                        lock.notify();
                    }
                }
            };
            mainHandler.post(preRunnable);

            synchronized (lock) {
                if(Looper.myLooper() == null)
                    Looper.prepare();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                onBackgroundResponse(call, response);

                Runnable postRunnable = new Runnable() {
                    @Override
                    public void run() {
                        onPostResponse(call, response);
                    }
                };
                mainHandler.post(postRunnable);
            }

        } else {
            throw new AsyncOnMainThreadException("You use AsyncRetrofit on main thread ");
        }

    }


    @Override
    public void onFailure(final Call<T> call, final Throwable throwable) {
        if (currentThread() != context.getMainLooper().getThread()) {
            Handler mainHandler = new Handler(context.getMainLooper());

            Runnable preRunnable = new Runnable() {
                @Override
                public void run() {
                    synchronized (lock) {
                        onPreFailure(call, throwable);
                        lock.notify();
                    }
                }
            };
            mainHandler.post(preRunnable);

            synchronized (lock) {
                if(Looper.myLooper() == null)
                    Looper.prepare();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                onBackgroundFailure(call, throwable);

                Runnable postRunnable = new Runnable() {
                    @Override
                    public void run() {
                        onPostFailure(call, throwable);
                    }
                };
                mainHandler.post(postRunnable);
            }
        } else {
            throw new AsyncOnMainThreadException("You use AsyncRetrofit on main thread ");
        }
    }

    public abstract void onPreResponse(Call<T> call, Response<T> response);

    public abstract void onBackgroundResponse(Call<T> call, Response<T> response);

    public abstract void onPostResponse(Call<T> call, Response<T> response);


    public abstract void onPreFailure(Call<T> call, Throwable throwable);

    public abstract void onBackgroundFailure(Call<T> call, Throwable throwable);

    public abstract void onPostFailure(Call<T> call, Throwable throwable);


}
