package jp.rei.andou.githubbrowser.presentation.authorization;

import android.arch.lifecycle.LiveData;

import jp.rei.andou.githubbrowser.presentation.general.Navigation;
import jp.rei.andou.githubbrowser.presentation.general.SingleEvent;

public interface SignInViewModel {

    void login();

    LiveData<SingleEvent<Integer>> getErrorToastMessages();

    LiveData<SingleEvent<Navigation>> getNavigationEvents();
}
