package ir.eligasht.eligashttest.features.basket;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import ir.eligasht.eligashttest.BaseActivity;
import ir.eligasht.eligashttest.POJOs.GoodListDatabaseModel;
import ir.eligasht.eligashttest.POJOs.UserBasketDatabaseModel;
import ir.eligasht.eligashttest.R;
import ir.eligasht.eligashttest.tools.Gen;
import timber.log.Timber;

@EActivity(R.layout.activity_basket)
public class BasketActivity extends BaseActivity implements BasketContract.View, onClick {
    BasketContract.Presenter presenter;
    @ViewById
    RecyclerView recycler;
    Realm realm;
    List<GoodListDatabaseModel> products;
    BasketListAdapter adapter;

    @AfterViews
    void initView() {
        presenter = new BasketPresenter(this);
        presenter.onActivityCreated();

    }

    @Click
    void back() {
        presenter.onBackClicked();
    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public void setUpRecycler() {
        try {
            realm = Realm.getDefaultInstance();
            products = new ArrayList<>();
            final List<UserBasketDatabaseModel> userModel = realm.where(UserBasketDatabaseModel.class).findAll();
            if (userModel.size() > 0)
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        for (int i = 0; i < userModel.size(); i++) {
                            GoodListDatabaseModel model = realm.where(GoodListDatabaseModel.class).equalTo("id", userModel.get(i).getProductId()).findFirst();
                            model.setBuyQuantity(userModel.get(i).getQuantity());
                            products.add(model);
                        }

                    }
                });
            adapter = new BasketListAdapter(mContext, products);
            recycler.setAdapter(adapter);
            recycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            adapter.setOnClick(BasketActivity.this);
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    @Override
    public void performeTheBuy() {
        try {
            realm = Realm.getDefaultInstance();
            final List<UserBasketDatabaseModel> userModel = realm.where(UserBasketDatabaseModel.class).findAll();
            if (userModel.size() > 0) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        for (int i = 0; i < userModel.size(); i++) {
                            GoodListDatabaseModel model = realm.where(GoodListDatabaseModel.class).equalTo("id", userModel.get(i).getProductId()).findFirst();
                            int qty = model.getQuantity();
                            Timber.d("the qty is beffore%s", qty);
                            if (qty - userModel.get(i).getQuantity() > 0) {
                                model.setQuantity(qty - userModel.get(i).getQuantity());
                                presenter.outOfQtyRequested();
                            }
                            realm.insertOrUpdate(model);
                            Timber.d("the qty is after%s", model.getQuantity());
                        }

                    }
                });
                presenter.onBuyingFinished();
            } else {
                presenter.listIsEmpty();
            }

        } finally {
            if (realm != null) {
                realm.close();
            }
        }

    }

    @Override
    public void showToast(int message) {
        Gen.showToast(mContext, getString(message));
    }

    @Click
    void done() {
        presenter.onDoneClicked();
    }

    @Override
    public void onItemClick(View view, final int position) {
        try {
            realm = Realm.getDefaultInstance();
            final List<UserBasketDatabaseModel> userModel = realm.where(UserBasketDatabaseModel.class).findAll();


            switch (view.getId()) {
                case R.id.minus:
                    if (userModel.size() > 0)
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                for (int i = 0; i < userModel.size(); i++) {
                                    if (i == position) {
                                        int currentQty = userModel.get(i).getQuantity();
                                        if (currentQty > 0) {
                                            userModel.get(i).setQuantity(--currentQty);
                                        }
                                    }
                                }

                            }
                        });
                    break;
                case R.id.plus:
                    if (userModel.size() > 0)
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                for (int i = 0; i < userModel.size(); i++) {
                                    if (i == position) {
                                        int currentQty = userModel.get(i).getQuantity();
                                        userModel.get(i).setQuantity(++currentQty);
                                    }
                                }

                            }
                        });
                    break;
                case R.id.remove:
                    if (userModel.size() > 0)
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                for (int i = 0; i < userModel.size(); i++) {
                                    if (i == position) {
                                        userModel.get(i).deleteFromRealm();
                                    }
                                }

                            }
                        });
                    break;
            }
        } finally {
            if (realm != null) {
                realm.close();
                presenter.onEditDone();
                finish();
            }
        }
    }
}
