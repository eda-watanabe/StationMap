package jp.inc.eda.yamanote_master.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by watanabe on 2017/07/20.
 */

public class Utils {

    private Utils(){}

    public static String assetJsonFileToString(Context context, String jsonFileName) {
        InputStream is = null;
        BufferedReader br = null;
        String json = "";
        try {
            try {
                is = context.getAssets().open(jsonFileName);
                br = new BufferedReader(new InputStreamReader(is));
                String str;
                while ((str = br.readLine()) != null) {
                    json += str + "\n";
                }
            } finally {
                if (is != null) is.close();
                if (br != null) br.close();
            }
        } catch (Exception e){
            // エラー発生時の処理
        }
        return json;
    }
}
