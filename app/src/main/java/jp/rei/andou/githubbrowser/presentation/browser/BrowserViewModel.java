package jp.rei.andou.githubbrowser.presentation.browser;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.jakewharton.rxrelay2.PublishRelay;

import jp.rei.andou.githubbrowser.data.entities.Repo;

public interface BrowserViewModel {

    PublishRelay<String> getSearchQuerySubject();
    LiveData<PagedList<Repo>> getPagedListLiveData();
}
