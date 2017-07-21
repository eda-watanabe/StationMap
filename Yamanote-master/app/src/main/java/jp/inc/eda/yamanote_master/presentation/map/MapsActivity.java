package jp.inc.eda.yamanote_master.presentation.map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import jp.inc.eda.yamanote_master.MyApp;
import jp.inc.eda.yamanote_master.R;
import jp.inc.eda.yamanote_master.domain.model.Station;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapsPresenter.Contract {

    private GoogleMap mMap;

    @Inject
    MapsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MyApp) getApplication()).getComponent().inject(this);
        presenter.setContract(this);

        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        presenter.mapReady();
    }

    @Override
    public void showStations(List<Station> stations) {
        for (Station s : stations) {
            float color = BitmapDescriptorFactory.HUE_GREEN;
            if (s.lineCode == Station.YAMANOTE) {
                color = BitmapDescriptorFactory.HUE_GREEN;
            } else if (s.lineCode == Station.CHUOU){
                color = BitmapDescriptorFactory.HUE_YELLOW;
            } else if (s.lineCode == Station.YOKOHAMA) {
                color = BitmapDescriptorFactory.HUE_BLUE;
            }
            MarkerOptions opt = new MarkerOptions()
                    .position(s.getLatLng())
                    .title(s.name)
                    .icon(BitmapDescriptorFactory.defaultMarker(color));
            mMap.addMarker(opt);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stations.get(0).getLatLng(), 10));

    }
}
