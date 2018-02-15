package ir.eligasht.eligashttest.features.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import ir.eligasht.eligashttest.BaseActivity;
import ir.eligasht.eligashttest.POJOs.GoodListDatabaseModel;
import ir.eligasht.eligashttest.R;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;
import timber.log.Timber;

/**
 *
 * No Enough Time to make interactors to get Data or put comments :)
 *
 *
 *
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainContract.View {
    @ViewById
    RecyclerView itList;
    @ViewById
    RecyclerView homeList;
    @ViewById
    BannerSlider slider;
    MainContract.Presenter presenter;
    Realm realm = null;
    GoodListAdapter itAdapter;
    GoodListAdapter homeAdapter;

    @AfterViews
    void initView() {
        presenter = new MainPresenter(this);
        presenter.onActivityreated();
    }

    @Override
    public void putDataIntoDatabase() {
        try {
            final int[] itPics = {
                    R.drawable.phone
            };
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    for (int i = 0; i < 30; i++) {
                        GoodListDatabaseModel realmModel = new GoodListDatabaseModel();
                        realmModel.setId(i);
                        realmModel.setName("موبایل وان پلاس " + i);
                        realmModel.setCategory(1);
                        realmModel.setDescription("موبایل وان پلاس بسیار فوق العاده و قوی است");
                        realmModel.setPrice(200000);
                        realmModel.setQuantity(50);
                        realmModel.setPicAddress(itPics[0]);
                        realm.insertOrUpdate(realmModel);
                    }
                    final int[] homePics = {
                            R.drawable.home
                    };
                    for (int i = 30; i < 60; i++) {
                        GoodListDatabaseModel realmModel = new GoodListDatabaseModel();
                        realmModel.setId(i);
                        realmModel.setName("لوازم خانگی " + i);
                        realmModel.setCategory(2);
                        realmModel.setDescription("موبایل الجی بسیار فوق العاده و قوی است");
                        realmModel.setPrice(200000);
                        realmModel.setQuantity(50);
                        realmModel.setPicAddress(homePics[0]);
                        realm.insertOrUpdate(realmModel);
                    }

                }
            });
        } finally {
            if (realm != null) {
//                realm.close();
            }
            presenter.onAllDataInsertedCorrectly();
        }
    }

    @Override
    public void showLists() {
        try {
            realm = Realm.getDefaultInstance();
            final List<GoodListDatabaseModel> itProducts = realm.where(GoodListDatabaseModel.class).equalTo("category", 1).findAll();
            final List<GoodListDatabaseModel> homeProducts = realm.where(GoodListDatabaseModel.class).equalTo("category", 2).findAll();
            itList.setAdapter(new GoodListAdapter(mContext, itProducts));
            itList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
            homeList.setAdapter(new GoodListAdapter(mContext, homeProducts));
            homeList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    @Override
    public void setUpSlider() {
        List<Banner> banners = new ArrayList<>();
        banners.add(new DrawableBanner(R.drawable.it_banner));
        banners.add(new DrawableBanner(R.drawable.home_banner));
        slider.setBanners(banners);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAllDataInsertedCorrectly();
    }
}
