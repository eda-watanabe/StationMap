package jp.inc.eda.yamanote_master.infra.repository;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import jp.inc.eda.yamanote_master.domain.model.Station;
import jp.inc.eda.yamanote_master.infra.dao.StationDao;

/**
 * Created by watanabe on 2017/07/20.
 */

public class StationRepository {

    private Context context;
    private StationDao stationDao;

    @Inject
    public StationRepository(Context context, StationDao stationDao) {
        this.context = context;
        this.stationDao = stationDao;
    }

    public Single<List<Station>> getStations(final long lineCode) {
        return Single.create(new SingleOnSubscribe<List<Station>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<Station>> e) throws Exception {
                List<Station> stations = stationDao.findByLineCode(lineCode);
                if (stations != null && stations.size() != 0) {
                    e.onSuccess(stations);
                }
            }
        });
    }

    public Single<List<Station>> getAll() {
        return Single.create(new SingleOnSubscribe<List<Station>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<Station>> e) throws Exception {
                e.onSuccess(stationDao.findAll());
            }
        });
    }
}
