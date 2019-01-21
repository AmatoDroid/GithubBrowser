package jp.rei.andou.githubbrowser.presentation.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.App;
import jp.rei.andou.githubbrowser.R;
import jp.rei.andou.githubbrowser.presentation.general.GeneralNavigator;

public class MainActivity extends AppCompatActivity {

    @Inject
    GeneralNavigator generalNavigator;
    @Inject
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).inject(this);
        DataBindingUtil.setContentView(this, R.layout.activity_main)
                .setLifecycleOwner(this);
        if (viewModel.isUserSessionAlive()) {
            generalNavigator.routeToBrowserScreen();
        } else {
            generalNavigator.routeToAuthorizationScreen();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            ((App) getApplication()).destroy(this);
        }
    }

}
