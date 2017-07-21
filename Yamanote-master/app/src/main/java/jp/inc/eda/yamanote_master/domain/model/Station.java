package jp.inc.eda.yamanote_master.domain.model;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;
import com.google.android.gms.maps.model.LatLng;

import jp.inc.eda.yamanote_master.infra.model.ApiStation;

/**
 * Created by watanabe on 2017/07/20.
 */
@Table
public class Station {
    private static final String TAG = "Station";

    public static final long YAMANOTE = 11302;
    public static final long CHUOU = 11313;
    public static final long YOKOHAMA = 11306;


    @PrimaryKey(auto = false)
    public long code;
    @Column
    public long globalCode;
    @Column(indexed = true)
    public long lineCode;
    @Column
    public String name;
    @Column
    public double lon;
    @Column
    public double lat;

    public static Station newInstance(long lineCode, ApiStation apiStation) {
        Station s = new Station();
        s.code = apiStation.station_cd;
        s.globalCode = apiStation.station_g_cd;
        s.lineCode = lineCode;
        s.name = apiStation.station_name;
        s.lon = apiStation.lon;
        s.lat = apiStation.lat;
        return s;
    }

    public LatLng getLatLng() {
        return new LatLng(lat, lon);
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + code +
                ", globalCode=" + globalCode +
                ", lineCode=" + lineCode +
                ", name='" + name + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
