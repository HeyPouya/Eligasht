package ir.eligasht.eligashttest.tools;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import ir.eligasht.eligashttest.R;


/**
 * Created by info on 11/16/2016.
 */
@EFragment(R.layout.fragment_waiting)
public class FragmentWaiting extends DialogFragment {


    @AfterViews
    void setLoading_view() {
        setCancelable(false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (this.isAdded())
            return;
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commit();
        } catch (IllegalStateException e) {
        }
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            //dialog not show
        }
    }
}
