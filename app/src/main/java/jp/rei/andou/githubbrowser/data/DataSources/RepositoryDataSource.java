package jp.rei.andou.githubbrowser.data.DataSources;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import jp.rei.andou.githubbrowser.data.entities.NetworkState;
import jp.rei.andou.githubbrowser.data.entities.Repo;
import jp.rei.andou.githubbrowser.data.repositories.GithubRepository;
import jp.rei.andou.githubbrowser.data.repositories.SessionRepository;
import jp.rei.andou.githubbrowser.helpers.LoadingHandlerTransformer;
import retrofit2.HttpException;

public class RepositoryDataSource extends PageKeyedDataSource<Long, Repo> {

    private final GithubRepository repository;
    private final MutableLiveData<NetworkState> requestState;
    private final SessionRepository sessionRepository;
    private final String query;
    private final Consumer<? super Throwable> errorCallback;

    RepositoryDataSource(String query, GithubRepository repository,
                         SessionRepository sessionRepository, MutableLiveData<NetworkState> requestState) {
        this.query = query;
        this.repository = repository;
        this.sessionRepository = sessionRepository;
        this.requestState = requestState;
        this.errorCallback = throwable -> {
            NetworkState networkState = NetworkState.FAILED;
            if (throwable instanceof HttpException) {
                networkState.setMessage("An server error has occurred");
            } else {
                networkState.setMessage("An internal error has occurred");
            }
            requestState.postValue(networkState);
        };
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Repo> callback) {
        if (query == null || query.isEmpty()) {
            return;
        }
        Disposable disposable = repository.searchRepositoriesByQuery(
                sessionRepository.getUserCredentials(), query, 1L, params.requestedLoadSize
        ).doOnSubscribe(s -> requestState.postValue(NetworkState.INITIAL_LOADING))
        .doOnSuccess((s) -> requestState.postValue(NetworkState.SUCCESS))
        .subscribe(repo -> callback.onResult(repo, 1L, 2L), errorCallback);

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Repo> callback) {}

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Repo> callback) {
        if (query == null || query.isEmpty()) {
            return;
        }
        Disposable disposable = repository.searchRepositoriesByQuery(
                sessionRepository.getUserCredentials(), query, params.key, params.requestedLoadSize
        ).compose(LoadingHandlerTransformer.create(requestState))
        .subscribe(repo -> callback.onResult(repo, params.key + 1), errorCallback);
    }
}
