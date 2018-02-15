package ir.eligasht.eligashttest.features.basket;

import ir.eligasht.eligashttest.R;

/**
 * Created by pouya on 2/15/18.
 */

public class BasketPresenter implements BasketContract.Presenter {
    private BasketContract.View view;

    public BasketPresenter(BasketContract.View view) {
        this.view = view;
    }

    @Override
    public void onActivityCreated() {
        view.setUpRecycler();
    }

    @Override
    public void onBackClicked() {
        view.finishView();
    }

    @Override
    public void onDoneClicked() {
        view.performeTheBuy();
    }

    @Override
    public void onBuyingFinished() {
        view.showToast(R.string.successful);
        view.finishView();
    }

    @Override
    public void outOfQtyRequested() {
        view.showToast(R.string.not_sucessful);
    }

    @Override
    public void onEditDone() {
        view.showToast(R.string.edit_done);
    }

    @Override
    public void listIsEmpty() {
        view.showToast(R.string.basket_is_empty);
    }
}
