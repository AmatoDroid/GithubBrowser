package jp.rei.andou.githubbrowser.presentation.main;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.domain.interactors.MainInteractor;

public class MainViewModelImpl extends ViewModel implements MainViewModel {

    private final MainInteractor mainInteractor;

    @Inject
    public MainViewModelImpl(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    @Override
    public boolean isUserSessionAlive() {
        return mainInteractor.isUserSessionAlive();
    }
}
