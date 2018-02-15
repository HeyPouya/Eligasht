package ir.eligasht.eligashttest;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;

import com.orhanobut.hawk.Hawk;

import org.jetbrains.annotations.NotNull;

import io.realm.Realm;
import ir.eligasht.eligashttest.tools.Consts;
import ir.eligasht.eligashttest.tools.Gen;
import timber.log.Timber;


/**
 * Created by info on 3/cluster_10/2017.
 */

public class MyApplication extends Application {

    public static Typeface mTypeface = null;
    public static Context context;
    public static Gen mGen;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override
    public void onCreate() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate();
        Realm.init(this);
        MyApplication.context = getApplicationContext();
        setTypeface(context);
        setmGen(context);
        Hawk.init(context).build();
        Timber.plant(new Timber.DebugTree() {
            @Nullable
            @Override
            protected String createStackElementTag(@NotNull StackTraceElement element) {
                return super.createStackElementTag(element) + ':' + element.getLineNumber();
            }
        });

    }


    public void setmGen(Context mContext) {
        if (mGen == null)
            mGen = new Gen(context);
    }

    public void setTypeface(Context mContext) {
        if (mTypeface == null)
            mTypeface = Typeface.createFromAsset(mContext.getAssets(), Consts.FONT_NAME);
    }


}
