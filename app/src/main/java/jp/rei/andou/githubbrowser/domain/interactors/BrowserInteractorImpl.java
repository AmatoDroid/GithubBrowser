package jp.rei.andou.githubbrowser.domain.interactors;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.data.DataSources.RepositoryDataFactory;
import jp.rei.andou.githubbrowser.data.entities.Repo;

public class BrowserInteractorImpl implements BrowserInteractor {

    private static final int PAGE_SIZE = 20;

    private final RepositoryDataFactory dataFactory;
    private LiveData<PagedList<Repo>> repositoryLiveData;

    @Inject
    public BrowserInteractorImpl(RepositoryDataFactory dataFactory) {
        this.dataFactory = dataFactory;
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
        PagedList pagedList = repositoryLiveData.getValue();
        if (pagedList == null) {
            return;
        }
        dataFactory.setQuery(query);
        pagedList.getDataSource().invalidate();
    }

    @Override
    public LiveData<PagedList<Repo>> getRepositoriesPagedList() {
        return repositoryLiveData;
    }


}
