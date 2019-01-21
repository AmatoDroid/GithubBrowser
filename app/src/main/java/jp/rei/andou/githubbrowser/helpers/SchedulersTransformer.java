package jp.rei.andou.githubbrowser.helpers;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulersTransformer<T> implements SingleTransformer<T, T>,
        ObservableTransformer<T, T>, MaybeTransformer<T, T>, CompletableTransformer {

    private SchedulersTransformer() {}

    public static <S> SchedulersTransformer<S> create() {
        return new SchedulersTransformer<>();
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream.observeOn(AndroidSchedulers.mainThread())
                       .subscribeOn(Schedulers.io());
    }

    @Override
    public CompletableSource apply(Completable upstream) {
        return upstream.observeOn(AndroidSchedulers.mainThread())
                       .subscribeOn(Schedulers.io());
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return upstream.observeOn(AndroidSchedulers.mainThread())
                       .subscribeOn(Schedulers.io());
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.observeOn(AndroidSchedulers.mainThread())
                       .subscribeOn(Schedulers.io());
    }
}