package ir.eligasht.eligashttest.features.details;

import ir.eligasht.eligashttest.R;

/**
 * Created by pouya on 2/15/18.
 */

public class DetailsPresenter implements DetailsContract.Presenter {
    private DetailsContract.View view;
    private static int quantity = 0;

    public DetailsPresenter(DetailsContract.View view) {

        this.view = view;
    }

    @Override
    public void onActivityCreated() {
        view.showDetails();
    }

    @Override
    public void onMinusClicked() {
        if (quantity > 0) {
            quantity--;
            view.showQuantity(quantity);
        }
    }

    @Override
    public void onPlusClicked() {
        if (quantity < 100) {
            quantity++;
            view.showQuantity(quantity);
        }
    }

    @Override
    public void onAddToBasketClicked() {
        if (quantity != 0 && view.hasEnoughQuantity(quantity))
            view.addToBasket(quantity);
        else
            view.showToast(R.string.not_enough_quantity);
    }

    @Override
    public void detachView() {
        quantity = 0;
        view = null;
    }

    @Override
    public void onShowBasketClicked() {
        view.gotoBasketView();
    }
}
