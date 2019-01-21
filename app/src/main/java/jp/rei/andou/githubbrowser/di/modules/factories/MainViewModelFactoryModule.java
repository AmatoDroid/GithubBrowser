package jp.rei.andou.githubbrowser.di.modules.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import jp.rei.andou.githubbrowser.di.ViewModelRecipe;
import jp.rei.andou.githubbrowser.di.scopes.MainScope;
import jp.rei.andou.githubbrowser.presentation.general.ViewModelFactory;
import jp.rei.andou.githubbrowser.presentation.main.MainViewModelImpl;

@Module
public abstract class MainViewModelFactoryModule {

    @Binds
    @MainScope
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @MainScope
    @ViewModelRecipe(MainViewModelImpl.class)
    abstract ViewModel bindMainViewModel(MainViewModelImpl mainViewModel);

}
