package ir.eligasht.eligashttest.features.basket;

/**
 * Created by pouya on 2/15/18.
 */

public class BasketContract {
    interface View {

        void finishView();

        void setUpRecycler();

        void performeTheBuy();

        void showToast(int message);
    }

    interface Presenter {

        void onActivityCreated();

        void onBackClicked();

        void onDoneClicked();

        void onBuyingFinished();

        void outOfQtyRequested();

        void onEditDone();

        void listIsEmpty();
    }
}
