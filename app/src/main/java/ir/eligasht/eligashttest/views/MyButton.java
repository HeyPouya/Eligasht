package ir.eligasht.eligashttest.views;

import android.content.Context;
import android.util.AttributeSet;

import ir.eligasht.eligashttest.MyApplication;


/**
 * Created by info on 9/30/2016.
 */

public class MyButton extends android.support.v7.widget.AppCompatButton {

    public MyButton(Context context) {
        super(context);
        this.setTypeface(MyApplication.mTypeface);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(MyApplication.mTypeface);
    }
}
