package jp.rei.andou.githubbrowser.di.modules;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import jp.rei.andou.githubbrowser.di.modules.factories.SignInViewModelFactoryModule;
import jp.rei.andou.githubbrowser.di.scopes.SignInScope;
import jp.rei.andou.githubbrowser.presentation.authorization.SignInFragment;
import jp.rei.andou.githubbrowser.presentation.authorization.SignInViewModel;
import jp.rei.andou.githubbrowser.presentation.authorization.SignInViewModelImpl;
import jp.rei.andou.githubbrowser.presentation.general.ViewModelFactory;

@Module(includes = SignInViewModelFactoryModule.class)
public class SignInModule {

    @Provides
    @SignInScope
    public static SignInViewModel provideSignInViewModel(SignInFragment fragment,
                                                         ViewModelFactory factory) {
        return ViewModelProviders.of(fragment, factory).get(SignInViewModelImpl.class);
    }
}
