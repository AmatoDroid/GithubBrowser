package jp.rei.andou.githubbrowser.presentation.authorization;

import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.R;
import jp.rei.andou.githubbrowser.presentation.browser.GithubBrowserFragment;

public class AuthorizationRouter implements AuthorizationNavigator {

    private final FragmentManager fragmentManager;

    @Inject
    public AuthorizationRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void routeToSignIn() {
        fragmentManager.beginTransaction()
                .replace(R.id.container, new SignInFragment())
                .commitAllowingStateLoss();
    }

    @Override
    public void routeToBrowserAsGuest() {
        fragmentManager.beginTransaction()
                .replace(R.id.container, new GithubBrowserFragment())
                .commitAllowingStateLoss();
    }

    @Override
    public void routeToBrowser(/*some user data to put to intent*/) {

    }
}
