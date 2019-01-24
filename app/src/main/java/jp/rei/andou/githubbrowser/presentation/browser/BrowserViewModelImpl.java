package jp.rei.andou.githubbrowser.presentation.browser;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.util.Log;

import com.jakewharton.rxrelay2.PublishRelay;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import jp.rei.andou.githubbrowser.data.entities.NetworkState;
import jp.rei.andou.githubbrowser.data.entities.Repo;
import jp.rei.andou.githubbrowser.domain.interactors.BrowserInteractor;
import jp.rei.andou.githubbrowser.presentation.general.Navigation;
import jp.rei.andou.githubbrowser.presentation.general.SingleEvent;
import lombok.Getter;

public class BrowserViewModelImpl extends ViewModel implements BrowserViewModel {


    private static final long DELAY = 500L;
    private final BrowserInteractor browserInteractor;
    private final PublishRelay<String> searchQuerySubject = PublishRelay.create();
    @Getter
    private final LiveData<NetworkState> networkState;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<SingleEvent<Navigation>> navigation = new MutableLiveData<>();

    @Inject
    public BrowserViewModelImpl(BrowserInteractor browserInteractor,
                                MutableLiveData<NetworkState> networkState) {
        this.browserInteractor = browserInteractor;
        this.networkState = networkState;
        browserInteractor.createRepositoryDataSource();
        listenSearchQuery();
    }

    private void listenSearchQuery() {
        Disposable disposable = searchQuerySubject.map(String::trim)
                .debounce(DELAY, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribe(query -> {
                    Log.d("QUERY", query);
                    browserInteractor.newSearch(query);
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public PublishRelay<String> getSearchQuerySubject() {
        return searchQuerySubject;
    }

    @Override
    public LiveData<PagedList<Repo>> getPagedListLiveData() {
        return browserInteractor.getRepositoriesPagedList();
    }

    @Override
    public LiveData<NetworkState> getNetworkStateLiveData() {
        return networkState;
    }

    @Override
    public void retrySearch() {
        browserInteractor.retrySearch();
    }

    @Override
    public boolean isUserSessionIsAlive() {
        return browserInteractor.isUserSessionIsAlive();
    }

    @Override
    public void navigateUser() {
        if (isUserSessionIsAlive()) {
            browserInteractor.logout();
            navigation.postValue(new SingleEvent<>(Navigation.WELCOME));
        } else {
            navigation.postValue(new SingleEvent<>(Navigation.SIGN_IN));
        }
    }

    @Override
    public LiveData<SingleEvent<Navigation>> getNavigationEvents() {
        return navigation;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
