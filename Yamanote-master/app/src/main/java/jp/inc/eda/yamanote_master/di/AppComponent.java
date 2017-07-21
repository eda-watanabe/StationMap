package jp.inc.eda.yamanote_master.di;

import javax.inject.Singleton;

import dagger.Component;
import jp.inc.eda.yamanote_master.presentation.launcher.LauncherActivity;
import jp.inc.eda.yamanote_master.presentation.map.MapsActivity;

/**
 * Created by watanabe on 2017/07/20.
 */
@Singleton
@Component(modules = {AppModule.class, InfraModule.class})
public interface AppComponent {
    void inject(LauncherActivity activity);
    void inject(MapsActivity activity);
}
