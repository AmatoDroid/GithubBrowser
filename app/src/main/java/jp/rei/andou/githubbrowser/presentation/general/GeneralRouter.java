package jp.rei.andou.githubbrowser.presentation.general;

import android.support.annotation.Nullable;
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
//        WelcomeFragment retainFragment = (WelcomeFragment) fragmentManager.findFragmentByTag(
//                WELCOME_FRAGMENT_TAG
//        );
//        retainFragment = retainFragment == null ? new WelcomeFragment() : retainFragment;
        route(new WelcomeFragment(), WELCOME_FRAGMENT_TAG);
    }

    @Override
    public void routeToBrowserScreen() {
//        GithubBrowserFragment retainFragment = (GithubBrowserFragment) fragmentManager.findFragmentByTag(
//                BROWSER_FRAGMENT_TAG
//        );
//        retainFragment = retainFragment == null ? new GithubBrowserFragment() : retainFragment;
        route(new GithubBrowserFragment(), BROWSER_FRAGMENT_TAG);
    }

    @Override
    public void routeToSignInScreen() {
//        SignInFragment retainFragment = getRetainedSignInFragment();
//        retainFragment = retainFragment == null ?  : retainFragment;
        route(new SignInFragment(), SIGN_IN_FRAGMENT_TAG);
    }

    @Nullable
    private WelcomeFragment getRetainedWelcomeFragment() {
        return (WelcomeFragment) fragmentManager.findFragmentByTag(WELCOME_FRAGMENT_TAG);
    }

    @Nullable
    private SignInFragment getRetainedSignInFragment() {
        return (SignInFragment) fragmentManager.findFragmentByTag(SIGN_IN_FRAGMENT_TAG);
    }

    @Nullable
    private GithubBrowserFragment getRetainedBrowserFragment() {
        return (GithubBrowserFragment) fragmentManager.findFragmentByTag(BROWSER_FRAGMENT_TAG);
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
