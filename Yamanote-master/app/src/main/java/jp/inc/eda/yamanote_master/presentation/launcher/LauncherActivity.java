package jp.inc.eda.yamanote_master.presentation.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import jp.inc.eda.yamanote_master.MyApp;
import jp.inc.eda.yamanote_master.R;
import jp.inc.eda.yamanote_master.presentation.map.MapsActivity;

public class LauncherActivity extends AppCompatActivity implements LauncherPresenter.Contract {

    @Inject
    LauncherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        ((MyApp) getApplication()).getComponent().inject(this);
        presenter.setContract(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void startMaps() {
        startActivity(new Intent(this, MapsActivity.class));
    }
}
