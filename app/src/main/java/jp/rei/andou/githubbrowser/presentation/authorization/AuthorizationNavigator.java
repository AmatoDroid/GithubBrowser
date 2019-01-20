package jp.rei.andou.githubbrowser.presentation.authorization;

public interface AuthorizationNavigator {

    void routeToSignIn();
    void routeToBrowserAsGuest();
    void routeToBrowser();

}
