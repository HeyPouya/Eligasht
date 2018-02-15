package ir.eligasht.eligashttest.views;

import android.content.Context;
import android.util.AttributeSet;

import ir.eligasht.eligashttest.MyApplication;
import ir.eligasht.eligashttest.tools.Gen;


/**
 * Created by info on 9/30/2016.
 */

public class MyTextView extends android.support.v7.widget.AppCompatTextView {

    public MyTextView(Context context) {
        super(context);
        if (!isInEditMode())
            this.setTypeface(MyApplication.mTypeface);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            this.setTypeface(MyApplication.mTypeface);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        try {
            String t = Gen.convertNumbersToPersian(text.toString());
            if (null != t)
                super.setText((CharSequence) t, type);
        } catch (Exception e) {
        }
    }
}
