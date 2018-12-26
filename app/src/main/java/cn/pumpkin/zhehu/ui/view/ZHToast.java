package cn.pumpkin.zhehu.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;
/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/24 19:40
 * @des:
 * @see {@link }
 */

public class ZHToast {

    private static Toast sToast ;

    private ZHToast(){

    }

    public static void showToast(Context context , String text){
        showToastInner(context,text,Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context , int stringId){
        showToastInner(context,context.getString(stringId),Toast.LENGTH_SHORT);
    }


    public static void showToastLong(Context context , String text ){
        showToastInner(context,text,Toast.LENGTH_LONG);
    }

    public static void showToastLong(Context context , int stringId){
        showToastInner(context,context.getString(stringId),Toast.LENGTH_LONG);
    }


    private static void showToastInner(Context context , String text , int duration){
        ensureToast( context);
        sToast.setText(text);
        sToast.setDuration(duration);
        sToast.show();
    }

    @SuppressLint("ShowToast")
    private static void ensureToast(Context context) {
        if(sToast!=null){
            return;
        }
        synchronized (ZHToast.class){
            if(sToast!=null) {
                return;
            }
            sToast =Toast.makeText(context.getApplicationContext()," ",Toast.LENGTH_SHORT);
        }
    }
}
