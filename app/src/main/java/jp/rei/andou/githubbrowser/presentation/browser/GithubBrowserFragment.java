package jp.rei.andou.githubbrowser.presentation.browser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.presentation.general.GeneralNavigator;

public class GithubBrowserFragment extends Fragment {

    @Inject
    GeneralNavigator generalNavigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo inject instance and inject navigator
        //todo 2: design layouts and set navigators to databindings layouts
    }
}
