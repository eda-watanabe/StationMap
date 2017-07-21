package jp.inc.eda.yamanote_master.infra.network;

import io.reactivex.Observable;
import jp.inc.eda.yamanote_master.infra.model.ApiLine;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by watanabe on 2017/07/21.
 */

public interface StationDataService {
    //http://www.ekidata.jp/api/l/(路線コード).json
    @GET("api/l/{code}.json")
    Observable<ApiLine> getLine(@Path("code") String code);
}
