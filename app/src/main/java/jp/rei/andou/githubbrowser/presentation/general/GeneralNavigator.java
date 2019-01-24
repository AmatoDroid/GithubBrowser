package jp.rei.andou.githubbrowser.presentation.general;

public interface GeneralNavigator {

    void routeToAuthorizationScreen();
    void routeToBrowserScreen();
    void routeToSignInScreen();

    boolean routeToRetainedFragment();
}
