package jp.inc.eda.yamanote_master.infra.dao;

import com.github.gfx.android.orma.annotation.OnConflict;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jp.inc.eda.yamanote_master.domain.model.Line;
import jp.inc.eda.yamanote_master.domain.model.OrmaDatabase;

/**
 * Created by watanabe on 2017/07/20.
 */

public class LineDao {

    private OrmaDatabase ormaDatabase;

    @Inject
    public LineDao(OrmaDatabase ormaDatabase) {
        this.ormaDatabase = ormaDatabase;
    }

    public void insert(final Line line) {
        ormaDatabase.transactionSync(new Runnable() {
            @Override
            public void run() {
                ormaDatabase.prepareInsertIntoLine(OnConflict.REPLACE).execute(line);
            }
        });
    }

    public Single<Line> find(final long code) {
        return Single.create(new SingleOnSubscribe<Line>() {
            @Override
            public void subscribe(@NonNull final SingleEmitter<Line> e) throws Exception {
                ormaDatabase.selectFromLine()
                        .codeEq(code)
                        .executeAsObservable()
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<Line>() {
                            @Override
                            public void accept(@NonNull Line line) throws Exception {
                                e.onSuccess(line);
                            }
                        });
            }
        });

    }

    public Single<List<Line>> findAll() {
        return Single.create(new SingleOnSubscribe<List<Line>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<Line>> e) throws Exception {
                e.onSuccess(ormaDatabase.selectFromLine().toList());
            }
        });
    }

//    public List<Line> findAll() {
//        return ormaDatabase.selectFromLine().toList();
//    }

}
