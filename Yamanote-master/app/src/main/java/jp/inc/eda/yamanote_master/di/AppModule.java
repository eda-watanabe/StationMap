package jp.inc.eda.yamanote_master.di;

import android.content.Context;

import com.github.gfx.android.orma.AccessThreadConstraint;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.inc.eda.yamanote_master.domain.model.OrmaDatabase;

/**
 * Created by watanabe on 2017/07/20.
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public OrmaDatabase provideOrmaDatabase() {
        return OrmaDatabase.builder(context)
                .writeOnMainThread(AccessThreadConstraint.FATAL)
                .readOnMainThread(AccessThreadConstraint.FATAL)
                .trace(true).build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }
}
