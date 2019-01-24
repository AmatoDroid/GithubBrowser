package jp.rei.andou.githubbrowser.presentation.browser;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.jakewharton.rxrelay2.PublishRelay;

import jp.rei.andou.githubbrowser.data.entities.NetworkState;
import jp.rei.andou.githubbrowser.data.entities.Repo;
import jp.rei.andou.githubbrowser.presentation.general.Navigation;
import jp.rei.andou.githubbrowser.presentation.general.SingleEvent;

public interface BrowserViewModel {

    PublishRelay<String> getSearchQuerySubject();
    LiveData<PagedList<Repo>> getPagedListLiveData();
    LiveData<NetworkState> getNetworkStateLiveData();
    void retrySearch();
    boolean isUserSessionIsAlive();
    void navigateUser();
    LiveData<SingleEvent<Navigation>> getNavigationEvents();
}
