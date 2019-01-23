package jp.rei.andou.githubbrowser.presentation.browser;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.util.Log;

import com.jakewharton.rxrelay2.PublishRelay;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import jp.rei.andou.githubbrowser.data.entities.Repo;
import jp.rei.andou.githubbrowser.domain.interactors.BrowserInteractor;

public class BrowserViewModelImpl extends ViewModel implements BrowserViewModel {


    private final BrowserInteractor browserInteractor;
    private final PublishRelay<String> searchQuerySubject = PublishRelay.create();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public BrowserViewModelImpl(BrowserInteractor browserInteractor) {
        this.browserInteractor = browserInteractor;
        browserInteractor.createRepositoryDataSource();
        listenSearchQuery();
    }

    private void listenSearchQuery() {
        Disposable disposable = searchQuerySubject.map(String::trim)
                .debounce(250, TimeUnit.MILLISECONDS)
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
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
