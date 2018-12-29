package cn.pumpkin.net;

import org.json.JSONObject;

import java.util.HashMap;

import cn.pumpkin.net.model.JuHeNews;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/11 12:11
 * @des:
 * @see {@link }
 */

public class ZhehuWrapper extends RetrofitManager{
    public final static String TAG = ZhehuWrapper.class.getSimpleName();
    private final int pageSize = 10;

    /**
     * 下面是两种不同的提交方式
     * */
    public Observable<JuHeNews> getJuHeInfo(JSONObject request) {
        RequestBody body = RequestBody.create(RETROFIT_JSON,request.toString());
        return getService().getJuHeMessage(body).compose(this.<JuHeNews>applySchedulers());
    }
    /**
     * 获取聚合数据测试
     * */
    public Observable<JuHeNews> getJuHeInfo(HashMap<String, String> request) {
        RequestBody body = RetrofitManager.createRequestBody(request);
        return getService().getJuHeMessage(body).compose(this.<JuHeNews>applySchedulers());
    }

}
