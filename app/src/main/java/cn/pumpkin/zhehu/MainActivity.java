package cn.pumpkin.zhehu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;

import org.json.JSONObject;

import java.util.HashMap;

import cn.pumpkin.zhehu.network.ZhehuWrapper;
import cn.pumpkin.zhehu.network.model.JuHeNews;
import cn.pumpkin.zhehu.network.params.JsonParamsBuild;
import cn.pumpkin.zhehu.ui.activity.BaseActivity;
import cn.pumpkin.zhehu.ui.activity.BaseFloatActivity;
import cn.pumpkin.zhehu.ui.home.HomeActivity;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getName();
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ZhehuWrapper wrapper = new ZhehuWrapper();

        mBtn = (Button) findViewById(R.id.button);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = JsonParamsBuild.buildJuHeNews("top", "6cf780f700fb7211695fc721665194**");

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("type", "top");
                hashMap.put("key", "6cf780f700fb7211695fc721665194**");

                Subscription subscription = wrapper.getJuHeInfo(hashMap/*jsonObject*/)
                        .subscribe(zheHuSubscriber(new Action1<JuHeNews>() {
                            @Override
                            public void call(JuHeNews o) {
                                Log.d(TAG, "-- login " + "Success : " + o.getResult().getData().get(0).getTitle());
                                goNext(o);
                            }
                        }, null));
                mCompositeSubscription.add(subscription);
            }
        });
    }

    public void goNext(JuHeNews news) {
        Toast.makeText(getApplicationContext(), "" + news.getResult().getData().get(0).getTitle(), Toast.LENGTH_SHORT).show();
    }
}
