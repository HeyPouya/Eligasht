package ir.eligasht.eligashttest.views;

import android.content.Context;
import android.util.AttributeSet;

import ir.eligasht.eligashttest.MyApplication;


/**
 * Created by info on 3/12/2017.
 */

public class MyRadioButton extends android.support.v7.widget.AppCompatRadioButton {

    public MyRadioButton(Context context) {
        super(context);
//        if (!isInEditMode())
        this.setTypeface(MyApplication.mTypeface);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
//        if (!isInEditMode())
        this.setTypeface(MyApplication.mTypeface);
    }

}
