package jp.inc.eda.yamanote_master.presentation.launcher;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jp.inc.eda.yamanote_master.infra.repository.LineRepository;
import jp.inc.eda.yamanote_master.infra.repository.StationRepository;

/**
 * Created by watanabe on 2017/07/21.
 */

public class LauncherPresenter {

    private CompositeDisposable disposables = new CompositeDisposable();

    LineRepository lineRepository;
    StationRepository stationRepository;
    Contract contract;

    @Inject
    public LauncherPresenter(LineRepository lineRepository,
                             StationRepository stationRepository) {
        this.lineRepository = lineRepository;
        this.stationRepository = stationRepository;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void start() {
        disposables.add(lineRepository.seedData()
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean line) throws Exception {
                        if (contract == null) return;
                        contract.startMaps();
                    }
                }));
    }

    public void destroy() {
        disposables.dispose();
    }

    public interface Contract {
        void startMaps();
    }
}
