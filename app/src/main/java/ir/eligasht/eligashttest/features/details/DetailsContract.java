package ir.eligasht.eligashttest.features.details;

/**
 * Created by pouya on 2/15/18.
 */

public class DetailsContract {
    interface View {

        void showDetails();

        boolean hasEnoughQuantity(int qty);

        void addToBasket(int qty);

        void showQuantity(int quantity);

        void showToast(int message);

        void gotoBasketView();
    }

    interface Presenter {

        void onActivityCreated();

        void onMinusClicked();

        void onPlusClicked();

        void onAddToBasketClicked();

        void detachView();

        void onShowBasketClicked();
    }
}
