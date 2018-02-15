package ir.eligasht.eligashttest.views;

import android.content.Context;
import android.util.AttributeSet;

import ir.eligasht.eligashttest.MyApplication;


/**
 * Created by info on 3/12/2017.
 */

public class MyCheckBox extends android.support.v7.widget.AppCompatCheckBox {

    public MyCheckBox(Context context) {
        super(context);
        this.setTypeface(MyApplication.mTypeface);
    }

    public MyCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(MyApplication.mTypeface);
    }

}
