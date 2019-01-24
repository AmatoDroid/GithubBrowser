package jp.rei.andou.githubbrowser.domain.interactors;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.data.DataSources.RepositoryDataFactory;
import jp.rei.andou.githubbrowser.data.entities.Repo;
import jp.rei.andou.githubbrowser.data.repositories.SessionRepository;

public class BrowserInteractorImpl implements BrowserInteractor {

    private static final int PAGE_SIZE = 20;

    private final RepositoryDataFactory dataFactory;
    private final SessionRepository sessionRepository;
    private LiveData<PagedList<Repo>> repositoryLiveData;

    @Inject
    public BrowserInteractorImpl(RepositoryDataFactory dataFactory, SessionRepository repository) {
        this.dataFactory = dataFactory;
        this.sessionRepository = repository;
    }

    @Override
    public void createRepositoryDataSource() {
        Executor executor = Executors.newFixedThreadPool(5);
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE * 2)
                .build();
        repositoryLiveData =  new LivePagedListBuilder<>(dataFactory, config)
                .setFetchExecutor(executor)
                .build();
    }

    @Override
    public void newSearch(String query) {
        dataFactory.setQuery(query);
        retrySearch();
    }

    @Override
    public void retrySearch() {
        PagedList pagedList = repositoryLiveData.getValue();
        if (pagedList != null) {
            pagedList.getDataSource().invalidate();
        }
    }

    @Override
    public LiveData<PagedList<Repo>> getRepositoriesPagedList() {
        return repositoryLiveData;
    }

    @Override
    public boolean isUserSessionIsAlive() {
        return sessionRepository.isUserSessionAlive();
    }

    @Override
    public void logout() {
        sessionRepository.logout();
    }


}
