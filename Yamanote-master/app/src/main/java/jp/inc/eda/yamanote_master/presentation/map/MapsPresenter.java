package jp.inc.eda.yamanote_master.presentation.map;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jp.inc.eda.yamanote_master.domain.model.Station;
import jp.inc.eda.yamanote_master.infra.repository.StationRepository;

/**
 * Created by watanabe on 2017/07/21.
 */

public class MapsPresenter {

    private StationRepository repository;
    private Contract contract;

    @Inject
    public MapsPresenter(StationRepository repository) {
        this.repository = repository;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void mapReady(){

        repository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Station>>() {
                    @Override
                    public void accept(@NonNull List<Station> stations) throws Exception {
                        if (contract == null) {
                            return;
                        }
                        contract.showStations(stations);
                    }
                });
    }

    public void destroy(){}

    interface Contract {
        void showStations(List<Station> stations);
    }
}
