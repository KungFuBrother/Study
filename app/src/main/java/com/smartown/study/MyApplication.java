package com.smartown.study;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.L;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-08 16:44
 * <p/>
 * 描述：
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    private void initImageLoader() {
        L.writeDebugLogs(false);
        L.writeLogs(false);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false).cacheInMemory(false)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                this).memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .defaultDisplayImageOptions(options).build();
        ImageLoader.getInstance().init(configuration);
    }


}
