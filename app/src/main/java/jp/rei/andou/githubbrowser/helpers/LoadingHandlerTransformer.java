package jp.rei.andou.githubbrowser.helpers;

import android.arch.lifecycle.MutableLiveData;

import org.reactivestreams.Publisher;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import jp.rei.andou.githubbrowser.data.entities.NetworkState;

public class LoadingHandlerTransformer<T> implements SingleTransformer<T, T>,
        ObservableTransformer<T, T>, MaybeTransformer<T, T>, FlowableTransformer<T, T>, CompletableTransformer {

    private final MutableLiveData<NetworkState> networkState;

    private LoadingHandlerTransformer(MutableLiveData<NetworkState> networkState) {
        this.networkState = networkState;
    }

    public static <S> LoadingHandlerTransformer<S> create(MutableLiveData<NetworkState> networkState) {
        return new LoadingHandlerTransformer<>(networkState);
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream.doOnSubscribe((r) -> networkState.postValue(NetworkState.RUNNING))
                .doAfterSuccess((s) -> networkState.postValue(NetworkState.SUCCESS));
    }

    @Override
    public CompletableSource apply(Completable upstream) {
       return upstream.doOnSubscribe((r) -> networkState.postValue(NetworkState.RUNNING))
               .doOnComplete(() -> networkState.postValue(NetworkState.SUCCESS));
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return upstream.doOnSubscribe((r) -> networkState.postValue(NetworkState.RUNNING))
                .doAfterSuccess((s) -> networkState.postValue(NetworkState.SUCCESS));
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.doOnSubscribe((r) -> networkState.postValue(NetworkState.RUNNING))
                .doOnComplete(() -> networkState.postValue(NetworkState.SUCCESS));
    }

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream.doOnSubscribe((r) -> networkState.postValue(NetworkState.RUNNING))
                .doOnComplete(() -> networkState.postValue(NetworkState.SUCCESS));
    }
}
