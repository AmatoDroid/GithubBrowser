package jp.rei.andou.githubbrowser.data.DataSources;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import io.reactivex.disposables.Disposable;
import jp.rei.andou.githubbrowser.data.entities.NetworkState;
import jp.rei.andou.githubbrowser.data.entities.Repo;
import jp.rei.andou.githubbrowser.data.repositories.GithubRepository;
import jp.rei.andou.githubbrowser.data.repositories.SessionRepository;

public class RepositoryDataSource extends PageKeyedDataSource<Long, Repo> {

    private final GithubRepository repository;
    private final MutableLiveData<NetworkState> requestState = new MutableLiveData<>();
    private final SessionRepository sessionRepository;
    private final String query;

    RepositoryDataSource(String query, GithubRepository repository,
                                SessionRepository sessionRepository) {
        this.query = query;
        this.repository = repository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Repo> callback) {
        requestState.postValue(NetworkState.RUNNING);
        Disposable disposable = repository.searchRepositoriesByQuery(
                sessionRepository.getUserCredentials(), query, 1L, params.requestedLoadSize
        ).subscribe(repo -> callback.onResult(repo, 1L, 2L), throwable -> {});
        // TODO: NetworkStates handling
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Repo> callback) {}

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Repo> callback) {
        requestState.postValue(NetworkState.RUNNING);
        Disposable disposable = repository.searchRepositoriesByQuery(
                sessionRepository.getUserCredentials(), query, params.key, params.requestedLoadSize
        ).subscribe(repo -> callback.onResult(repo, params.key + 1), throwable -> {});
    }
}
