package jp.rei.andou.githubbrowser.di.modules;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
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
    public static BrowserInteractor provideBrowserInteractor() {
        return new BrowserInteractorImpl();
    }

    @Provides
    @BrowserScope
    public static BrowserViewModel provideBrowserViewModel(GithubBrowserFragment fragment,
                                                    ViewModelFactory factory) {
        return ViewModelProviders.of(fragment, factory).get(BrowserViewModelImpl.class);
    }

}
