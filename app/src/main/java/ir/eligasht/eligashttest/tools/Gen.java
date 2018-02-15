package ir.eligasht.eligashttest.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.orhanobut.hawk.Hawk;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

import ir.eligasht.eligashttest.MyApplication;
import ir.eligasht.eligashttest.R;
import timber.log.Timber;


/**
 * Created by info on 3/cluster_10/2017.
 */

@EBean
public class Gen {

    Context mContext;


    public static FragmentWaiting fragmentWaiting;
    public static Gson gson;

    public static Gson getGson() {
        if (gson == null)
            gson = new Gson();
        return gson;
    }


    public Gen(Context mContext) {
        this.mContext = mContext;
    }


    public static void showToast(Context mContext, String message) {
        if (null == mContext)
            mContext = MyApplication.context;
        int color = ContextCompat.getColor(mContext, R.color.colorPrimary);

        StyleableToast st = new StyleableToast(mContext, message, Toast.LENGTH_LONG);
        st.setBackgroundColor(color);
        st.setTextColor(Color.WHITE);
        st.setIcon(R.mipmap.ic_launcher);
        st.setDuration(4500);
        st.setTextFont(MyApplication.mTypeface);
        st.setCornerRadius(5);
        st.show();
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void hideKeyboard(View view, Context context) {
        if (view != null) {
            try {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } catch (Exception ignore) {

            }
        }
    }

    public static String getPathFromIRL(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    public static String cleanCommaOnEnd(String str) {
        if (str.endsWith(","))
            return str.substring(0, str.length() - 1);
        return str;
    }


    public static Boolean isNull(String str) {
        if (!TextUtils.isEmpty(str))
            if (str.length() > 0)
                return false;
        return true;
    }


    public static Bitmap resizeMapIcons(Context mContext, String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(mContext.getResources(), mContext.getResources().getIdentifier(iconName, "drawable", mContext.getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public static FragmentWaiting getFragmentWaiting() {
        if (fragmentWaiting == null)
            fragmentWaiting = new FragmentWaiting_().builder().build();

        return fragmentWaiting;
    }

    public static void showFragmentWaiting(FragmentManager manager) {
        String tag = "showingNotif" + Math.random() * 100 + 50;
        if (fragmentWaiting == null)
            new FragmentWaiting_().builder().build().show(manager, tag);
        else
            fragmentWaiting.show(manager, tag);
    }


    public static void dismissWaiting() {
        try {
            if (null != Gen.getFragmentWaiting())
                if (Gen.getFragmentWaiting().isAdded())
                    Gen.getFragmentWaiting().dismiss();
        } catch (Exception e) {
            //waiting fragment not inited in parent component
        }
    }

    public static float dpToPx(int dp, Context mContext) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_MEDIUM));
    }


    public static void setListViewHeightBasedOnItems(ListView listView) {
        ListAdapter mAdapter = listView.getAdapter();
        int totalHeight = 0;
        int counterItem = (mAdapter.getCount());
        for (int i = 0; i < counterItem; i++) {
            View mView = mAdapter.getView(i, null, listView);
            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += mView.getMeasuredHeight();
            Log.w("HEIGHT" + i, String.valueOf(totalHeight));
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (mAdapter.getCount()));
//        params.height=params.height+350;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static void callPhone(Context mContext, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        mContext.startActivity(intent);
    }

    public static String convertNumbersToPersian(String str) {
        String answer = str;
        answer = answer.replace("1", "١");
        answer = answer.replace("2", "٢");
        answer = answer.replace("3", "٣");
        answer = answer.replace("4", "٤");
        answer = answer.replace("5", "٥");
        answer = answer.replace("6", "٦");
        answer = answer.replace("7", "٧");
        answer = answer.replace("8", "٨");
        answer = answer.replace("9", "٩");
        answer = answer.replace("0", "٠");
        return answer;
    }

    public static String convertNumbersToEnglish(String str) {
        String answer = str;
        answer = answer.replace("١", "1");
        answer = answer.replace("٢", "2");
        answer = answer.replace("٣", "3");
        answer = answer.replace("٤", "4");
        answer = answer.replace("٥", "5");
        answer = answer.replace("٦", "6");
        answer = answer.replace("٧", "7");
        answer = answer.replace("٨", "8");
        answer = answer.replace("٩", "9");
        answer = answer.replace("٠", "0");
        return answer;
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    /**
     * Shows Waiting dialog
     *
     * @return
     */
    public static void changeTabsFont(TabLayout tabLayout, Activity activity) {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    AssetManager mgr = activity.getAssets();
                    Typeface tf = Typeface.createFromAsset(mgr, "fonts/IRANSansMobile.ttf");//Font file in /assets
                    ((TextView) tabViewChild).setTypeface(tf);
                }
            }
        }
    }


}