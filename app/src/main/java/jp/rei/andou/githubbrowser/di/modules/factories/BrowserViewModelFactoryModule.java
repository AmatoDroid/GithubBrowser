package jp.rei.andou.githubbrowser.di.modules.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import jp.rei.andou.githubbrowser.di.ViewModelRecipe;
import jp.rei.andou.githubbrowser.di.scopes.BrowserScope;
import jp.rei.andou.githubbrowser.presentation.browser.BrowserViewModelImpl;
import jp.rei.andou.githubbrowser.presentation.general.ViewModelFactory;

@Module
public abstract class BrowserViewModelFactoryModule {

    @Binds
    @BrowserScope
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @BrowserScope
    @ViewModelRecipe(BrowserViewModelImpl.class)
    abstract ViewModel bindMainViewModel(BrowserViewModelImpl mainViewModel);

}
