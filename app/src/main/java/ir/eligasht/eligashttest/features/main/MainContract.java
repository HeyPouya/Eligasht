package ir.eligasht.eligashttest.features.main;

/**
 * Created by pouya on 2/15/18.
 */

public class MainContract {
    interface View {

        void putDataIntoDatabase();

        void showLists();

        void setUpSlider();
    }

    interface Presenter {

        void onActivityreated();

        void onAllDataInsertedCorrectly();
    }
}
