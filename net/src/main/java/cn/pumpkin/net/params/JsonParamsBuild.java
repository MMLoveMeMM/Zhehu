package cn.pumpkin.net.params;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/11 14:49
 * @des:
 * @see {@link }
 */

public class JsonParamsBuild {

    public static JSONObject buildJuHeNews(String type,String key){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("type",type);
            jsonObject.put("key",key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
