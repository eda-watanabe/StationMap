package jp.inc.eda.yamanote_master.di;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.inc.eda.yamanote_master.domain.model.OrmaDatabase;
import jp.inc.eda.yamanote_master.infra.dao.LineDao;
import jp.inc.eda.yamanote_master.infra.dao.StationDao;
import jp.inc.eda.yamanote_master.infra.repository.LineRepository;
import jp.inc.eda.yamanote_master.infra.repository.StationRepository;

/**
 * Created by watanabe on 2017/07/20.
 */
@Module
public class InfraModule {

    private Context context;

    public InfraModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public LineDao provideLineDao(OrmaDatabase ormaDatabase) {
        return new LineDao(ormaDatabase);
    }

    @Singleton
    @Provides
    public StationDao provideStationDao(OrmaDatabase ormaDatabase) {
        return new StationDao(ormaDatabase);
    }

    @Provides
    @Singleton
    public LineRepository provideLineRepository(LineDao lineDao, StationDao stationDao, Gson gson) {
        return new LineRepository(context, lineDao, stationDao, gson);
    }

    @Provides
    @Singleton
    public StationRepository provideStationRepository(StationDao stationDao) {
        return new StationRepository(context, stationDao);
    }
}
