package jp.inc.eda.yamanote_master;

import android.app.Application;

import jp.inc.eda.yamanote_master.di.AppComponent;
import jp.inc.eda.yamanote_master.di.AppModule;
import jp.inc.eda.yamanote_master.di.DaggerAppComponent;
import jp.inc.eda.yamanote_master.di.InfraModule;

/**
 * Created by watanabe on 2017/07/20.
 */

public class MyApp extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .infraModule(new InfraModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
