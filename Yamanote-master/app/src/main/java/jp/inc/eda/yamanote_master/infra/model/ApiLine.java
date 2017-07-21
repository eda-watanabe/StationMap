package jp.inc.eda.yamanote_master.infra.model;

import java.util.List;

/**
 * Created by watanabe on 2017/07/20.
 * {"line_cd":11302,"line_name":"JR山手線","line_lon":139.73522275686264,"line_lat":35.69302730762992,"line_zoom":12,"station_l":
 */

public class ApiLine {

    public Long line_cd;
    public String line_name;
    public double line_lon;
    public double line_lat;
    public int line_zoom;
    public List<ApiStation> station_l;

    @Override
    public String toString() {
        return "ApiLine{" +
                "line_cd=" + line_cd +
                ", line_name='" + line_name + '\'' +
                ", line_lon=" + line_lon +
                ", line_lat=" + line_lat +
                ", line_zoom=" + line_zoom +
                ", station_l=" + station_l +
                '}';
    }
}
