package jp.inc.eda.yamanote_master.infra.repository;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import jp.inc.eda.yamanote_master.domain.model.Line;
import jp.inc.eda.yamanote_master.infra.dao.LineDao;
import jp.inc.eda.yamanote_master.infra.dao.StationDao;
import jp.inc.eda.yamanote_master.infra.model.ApiLine;
import jp.inc.eda.yamanote_master.utils.Utils;

/**
 * Created by watanabe on 2017/07/20.
 */

public class LineRepository {

    private Context context;
    private LineDao lineDao;
    private StationDao stationDao;
    private Gson gson;

    private String[] files = {
            "yamanote_station_info.json",
            "chuou_station_info.json",
            "yokohama_station_info.json"
    };

    @Inject
    public LineRepository(Context context, LineDao lineDao, StationDao stationDao, Gson gson) {
        this.context = context;
        this.lineDao = lineDao;
        this.stationDao = stationDao;
        this.gson = gson;
    }

    public Single<Boolean> seedData() {

        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Boolean> e) throws Exception {
                for (String f : files) {
                    String json = Utils.assetJsonFileToString(context, f);
                    ApiLine apiLine = gson.fromJson(json, ApiLine.class);
                    Line l = Line.newInstance(apiLine);
                    lineDao.insert(l);
                    stationDao.insert(l.stations);
                }

                e.onSuccess(true);
            }
        });
    }

}
