package jp.rei.andou.githubbrowser.di.modules;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import jp.rei.andou.githubbrowser.data.DataSources.RepositoryDataFactory;
import jp.rei.andou.githubbrowser.data.entities.NetworkState;
import jp.rei.andou.githubbrowser.data.repositories.GithubRepository;
import jp.rei.andou.githubbrowser.data.repositories.SessionRepository;
import jp.rei.andou.githubbrowser.di.modules.factories.BrowserViewModelFactoryModule;
import jp.rei.andou.githubbrowser.di.scopes.BrowserScope;
import jp.rei.andou.githubbrowser.domain.interactors.BrowserInteractor;
import jp.rei.andou.githubbrowser.domain.interactors.BrowserInteractorImpl;
import jp.rei.andou.githubbrowser.presentation.browser.BrowserViewModel;
import jp.rei.andou.githubbrowser.presentation.browser.BrowserViewModelImpl;
import jp.rei.andou.githubbrowser.presentation.browser.GithubBrowserFragment;
import jp.rei.andou.githubbrowser.presentation.general.ViewModelFactory;

@Module(includes = BrowserViewModelFactoryModule.class)
public abstract class BrowserModule {

    @Provides
    @BrowserScope
    public static MutableLiveData<NetworkState> provideNetworkStateLiveData() {
        return new MutableLiveData<>();
    }

    @Provides
    @BrowserScope
    public static RepositoryDataFactory provideRepositoryDataFactory(
            GithubRepository githubRepository,
            SessionRepository sessionRepository,
            MutableLiveData<NetworkState> networkStateMutableLiveData
    ) {
        return new RepositoryDataFactory(githubRepository, sessionRepository, networkStateMutableLiveData);
    }

    @Provides
    @BrowserScope
    public static BrowserInteractor provideBrowserInteractor(BrowserInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @BrowserScope
    public static BrowserViewModel provideBrowserViewModel(GithubBrowserFragment fragment,
                                                    ViewModelFactory factory) {
        return ViewModelProviders.of(fragment, factory).get(BrowserViewModelImpl.class);
    }

}
