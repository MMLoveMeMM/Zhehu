package cn.pumpkin.net;

import cn.pumpkin.net.model.JuHeNews;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/6 19:20
 * @des:
 * @see {@link }
 */
public interface ZhehuService {

    /**
     * 测试API
     * */
    @POST("/toutiao/index")
    //  此处回调返回的可为任意类型Call<T>，再也不用自己去解析json数据啦！！！
    Observable<JuHeNews> getJuHeMessage(@Body RequestBody body/*@Query("type") String type, @Query("key") String key*/);

}
