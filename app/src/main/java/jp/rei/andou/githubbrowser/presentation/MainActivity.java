package jp.rei.andou.githubbrowser.presentation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.App;
import jp.rei.andou.githubbrowser.R;
import jp.rei.andou.githubbrowser.presentation.authorization.AuthorizationNavigator;
import jp.rei.andou.githubbrowser.presentation.authorization.AuthorizationRouter;
import jp.rei.andou.githubbrowser.presentation.general.GeneralNavigator;
import jp.rei.andou.githubbrowser.presentation.general.GeneralRouter;

public class MainActivity extends AppCompatActivity {

    @Inject
    GeneralNavigator generalNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).inject(this);
        //todo viewModel
        DataBindingUtil.setContentView(this, R.layout.activity_main)
                .setLifecycleOwner(this);
        // TODO: SharedPreference session?? or Application scoped session?
        generalNavigator.routeToAuthorizationScreen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            ((App) getApplication()).destroy(this);
        }
    }

}
