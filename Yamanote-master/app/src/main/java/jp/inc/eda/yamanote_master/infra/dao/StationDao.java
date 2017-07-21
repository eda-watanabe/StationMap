package jp.inc.eda.yamanote_master.infra.dao;

import com.github.gfx.android.orma.annotation.OnConflict;

import java.util.List;

import javax.inject.Inject;

import jp.inc.eda.yamanote_master.domain.model.OrmaDatabase;
import jp.inc.eda.yamanote_master.domain.model.Station;

/**
 * Created by watanabe on 2017/07/20.
 */

public class StationDao {

    private OrmaDatabase ormaDatabase;

    @Inject
    public StationDao(OrmaDatabase ormaDatabase) {
        this.ormaDatabase = ormaDatabase;
    }

    public void insert(final List<Station> stations) {
        ormaDatabase.transactionSync(new Runnable() {
            @Override
            public void run() {
                ormaDatabase.prepareInsertIntoStation(OnConflict.REPLACE).executeAll(stations);
            }
        });
    }

    public List<Station> findByLineCode(long lineCode) {
        return ormaDatabase.selectFromStation().lineCodeEq(lineCode).toList();
    }

    public List<Station> findAll() {
        return ormaDatabase.selectFromStation().toList();
    }
}
