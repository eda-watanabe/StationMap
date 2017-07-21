package jp.inc.eda.yamanote_master.domain.model;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

import java.util.ArrayList;
import java.util.List;

import jp.inc.eda.yamanote_master.infra.model.ApiLine;
import jp.inc.eda.yamanote_master.infra.model.ApiStation;

/**
 * Created by watanabe on 2017/07/20.
 */
@Table
public class Line {
    @PrimaryKey(auto = false)
    public long code;
    @Column
    public String name;
    @Column
    public double lon;
    @Column
    public double lat;

    public List<Station> stations;

    public static Line newInstance(ApiLine apiLine) {
        Line l = new Line();
        l.code = apiLine.line_cd;
        l.name = apiLine.line_name;
        l.lon = apiLine.line_lon;
        l.lat = apiLine.line_lat;
        l.stations = new ArrayList<>();
        for (ApiStation s : apiLine.station_l) {
            l.stations.add(Station.newInstance(apiLine.line_cd, s));
        }
        return l;
    }
}
