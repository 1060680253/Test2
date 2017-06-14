package musicplus.com.test;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/6/14.
 */

public class App extends Application {

    private static App instance;

    public static synchronized App getInstance() {
        return instance;
    }

    public Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
//        if (BuildConfig.DEBUG) {
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                    .detectAll().penaltyLog().build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                    .detectAll().penaltyLog().build());
//        }
//        boolean isInSamplerProcess = BlockCanaryEx.isInSamplerProcess(this);
//        if(!isInSamplerProcess) {
//            BlockCanaryEx.install(new Config(this));
//        }
        super.onCreate();
        instance = this;
    }
}
