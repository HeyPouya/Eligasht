package ir.eligasht.eligashttest.views;

import android.content.Context;
import android.util.AttributeSet;

import ir.eligasht.eligashttest.MyApplication;


/**
 * Created by info on 9/30/2016.
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText {
    Context mContext;

    public MyEditText(Context context) {
        super(context);
        mContext = context;
        if (!isInEditMode())
            this.setTypeface(MyApplication.mTypeface);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        if (!isInEditMode())
            this.setTypeface(MyApplication.mTypeface);
    }

    public String text() {
        return this.getText().toString();
    }

    public int lenght() {
        return this.getText().length();
    }

}
