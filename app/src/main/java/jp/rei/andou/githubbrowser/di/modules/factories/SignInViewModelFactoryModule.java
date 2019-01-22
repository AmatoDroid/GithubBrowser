package jp.rei.andou.githubbrowser.di.modules.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import jp.rei.andou.githubbrowser.di.ViewModelRecipe;
import jp.rei.andou.githubbrowser.di.scopes.SignInScope;
import jp.rei.andou.githubbrowser.presentation.authorization.SignInViewModelImpl;
import jp.rei.andou.githubbrowser.presentation.general.ViewModelFactory;

@Module
public abstract class SignInViewModelFactoryModule {

    @Binds
    @SignInScope
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @SignInScope
    @ViewModelRecipe(SignInViewModelImpl.class)
    abstract ViewModel bindViewModel(SignInViewModelImpl signInViewModel);
}
