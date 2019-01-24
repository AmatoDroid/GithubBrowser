package jp.rei.andou.githubbrowser.presentation.authorization;

import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.R;
import jp.rei.andou.githubbrowser.presentation.browser.GithubBrowserFragment;

public class AuthorizationRouter implements AuthorizationNavigator {

    private final FragmentManager fragmentManager;
    private final static String BROWSER_FRAGMENT_TAG = "BROWSER_FRAGMENT_TAG";

    @Inject
    public AuthorizationRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }


    @Override
    public void routeToSignIn() {

    }

    @Override
    public void routeToBrowser() {
        GithubBrowserFragment retainFragment = (GithubBrowserFragment) fragmentManager.findFragmentByTag(
                BROWSER_FRAGMENT_TAG
        );
        retainFragment = retainFragment == null ? new GithubBrowserFragment() : retainFragment;
        fragmentManager.beginTransaction()
                .replace(R.id.container, retainFragment, BROWSER_FRAGMENT_TAG)
                .commitAllowingStateLoss();
    }
}
