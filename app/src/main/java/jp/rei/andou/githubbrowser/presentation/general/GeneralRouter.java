package jp.rei.andou.githubbrowser.presentation.general;

import android.support.v4.app.FragmentManager;

import jp.rei.andou.githubbrowser.R;
import jp.rei.andou.githubbrowser.presentation.authorization.WelcomeFragment;
import jp.rei.andou.githubbrowser.presentation.browser.GithubBrowserFragment;

public class GeneralRouter implements GeneralNavigator {

    private final FragmentManager fragmentManager;

    public GeneralRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void routeToAuthorizationScreen() {
        fragmentManager.beginTransaction()
                .replace(R.id.container, new WelcomeFragment())
                .commitAllowingStateLoss();
    }

    @Override
    public void routeToBrowserScreen() {
        fragmentManager.beginTransaction().replace(R.id.container, new GithubBrowserFragment())
                .commitAllowingStateLoss();
    }
}
