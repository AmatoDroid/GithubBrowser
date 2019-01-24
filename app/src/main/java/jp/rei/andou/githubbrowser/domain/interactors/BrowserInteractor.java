package jp.rei.andou.githubbrowser.domain.interactors;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import jp.rei.andou.githubbrowser.data.entities.Repo;

public interface BrowserInteractor {
    void createRepositoryDataSource();
    void newSearch(String query);
    void retrySearch();
    LiveData<PagedList<Repo>> getRepositoriesPagedList();
    boolean isUserSessionIsAlive();
    void logout();
}
