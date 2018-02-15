package ir.eligasht.eligashttest.features.main;

/**
 * Created by pouya on 2/15/18.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {

        this.view = view;
    }

    @Override
    public void onActivityreated() {
        view.setUpSlider();
        view.putDataIntoDatabase();
    }

    @Override
    public void onAllDataInsertedCorrectly() {
        view.showLists();
    }
}
