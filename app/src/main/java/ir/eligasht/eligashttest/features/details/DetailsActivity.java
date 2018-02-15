package ir.eligasht.eligashttest.features.details;

import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;
import ir.eligasht.eligashttest.BaseActivity;
import ir.eligasht.eligashttest.POJOs.GoodListDatabaseModel;
import ir.eligasht.eligashttest.POJOs.UserBasketDatabaseModel;
import ir.eligasht.eligashttest.R;
import ir.eligasht.eligashttest.features.basket.BasketActivity_;
import ir.eligasht.eligashttest.tools.Gen;
import ir.eligasht.eligashttest.views.MyTextView;

import static ir.eligasht.eligashttest.tools.Consts.ID;

@EActivity(R.layout.activity_details)
public class DetailsActivity extends BaseActivity implements DetailsContract.View {
    @Extra(ID)
    int id;
    @ViewById
    MyTextView name;
    @ViewById
    MyTextView price;
    @ViewById
    MyTextView quantity;
    @ViewById
    MyTextView description;
    @ViewById
    ImageView img;
    @ViewById
    MyTextView productQty;
    DetailsContract.Presenter presenter;
    Realm realm = null;

    @AfterViews
    void initView() {
        presenter = new DetailsPresenter(this);
        presenter.onActivityCreated();
    }

    @Override
    public void showDetails() {
        try {
            realm = Realm.getDefaultInstance();
            GoodListDatabaseModel model = realm.where(GoodListDatabaseModel.class).equalTo("id", id).findFirst();
            name.setText(model.getName());
            price.setText(String.format("%s %s", model.getPrice(), getString(R.string.toman)));
            quantity.setText(String.format("%s %s", model.getQuantity(), getString(R.string.numbers)));
            description.setText(String.format("%s : %s", getString(R.string.description), model.getDescription()));
            img.setImageResource(model.getPicAddress());

        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    @Override
    public boolean hasEnoughQuantity(int qty) {
        try {
            realm = Realm.getDefaultInstance();
            GoodListDatabaseModel model = realm.where(GoodListDatabaseModel.class).equalTo("id", id).findFirst();
            if (model != null)
                if (model.getQuantity() >= qty)
                    return true;

            return false;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    @Override
    public void addToBasket(int qty) {
        try {
            realm = Realm.getDefaultInstance();
            GoodListDatabaseModel model = realm.where(GoodListDatabaseModel.class).equalTo("id", id).findFirst();
            final UserBasketDatabaseModel userModel = new UserBasketDatabaseModel();
            final UserBasketDatabaseModel checkPreviousVersion = realm.where(UserBasketDatabaseModel.class).equalTo("productId", model.getId()).findFirst();
            userModel.setProductId(model.getId());
            userModel.setQuantity(qty);
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Number currentIdNum = realm.where(UserBasketDatabaseModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    if (checkPreviousVersion != null)
                        userModel.setId(checkPreviousVersion.getId());
                    else
                        userModel.setId(nextId);
                    realm.insertOrUpdate(userModel);
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
                Gen.showToast(mContext, "با موفقیت انجام شد");
            }
        }
    }

    @Override
    public void showQuantity(int quantity) {
        productQty.setText(String.valueOf(quantity));
    }

    @Override
    public void showToast(int message) {
        Gen.showToast(mContext, getString(message));
    }

    @Override
    public void gotoBasketView() {
        BasketActivity_.intent(mContext).start();
        presenter.detachView();
        finish();
    }

    @Click
    void minus() {
        presenter.onMinusClicked();
    }

    @Click
    void plus() {
        presenter.onPlusClicked();
    }

    @Click
    void basket() {
        presenter.onAddToBasketClicked();
    }

    @Click
    void back() {
        presenter.detachView();
        finish();
    }

    @Click
    void basketView() {
        presenter.onShowBasketClicked();
    }
}
