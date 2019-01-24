package jp.rei.andou.githubbrowser.presentation.general;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import jp.rei.andou.githubbrowser.R;
import jp.rei.andou.githubbrowser.presentation.authorization.SignInFragment;
import jp.rei.andou.githubbrowser.presentation.authorization.WelcomeFragment;
import jp.rei.andou.githubbrowser.presentation.browser.GithubBrowserFragment;

public class GeneralRouter implements GeneralNavigator {

    private final FragmentManager fragmentManager;
    private final static String WELCOME_FRAGMENT_TAG = "WELCOME_FRAGMENT_TAG";
    private final static String BROWSER_FRAGMENT_TAG = "BROWSER_FRAGMENT_TAG";
    private final static String SIGN_IN_FRAGMENT_TAG = "SIGN_IN_FRAGMENT_TAG";

    public GeneralRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void routeToAuthorizationScreen() {
        route(new WelcomeFragment(), WELCOME_FRAGMENT_TAG);
    }

    @Override
    public void routeToBrowserScreen() {
        route(new GithubBrowserFragment(), BROWSER_FRAGMENT_TAG);
    }

    @Override
    public void routeToSignInScreen() {
        route(new SignInFragment(), SIGN_IN_FRAGMENT_TAG);
    }

    private void route(Fragment fragment, String tag) {
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment, tag)
                .commitAllowingStateLoss();
    }

    @Override
    public boolean routeToRetainedFragment() {
        List<Fragment> retainFragments = fragmentManager.getFragments();
        if (retainFragments.isEmpty()) {
            return false;
        }
        Fragment retainedFragment = retainFragments.get(retainFragments.size() - 1);
        route(retainedFragment, retainedFragment.getTag());
        return true;
    }
}
