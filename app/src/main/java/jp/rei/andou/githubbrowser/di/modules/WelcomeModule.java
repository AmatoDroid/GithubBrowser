package jp.rei.andou.githubbrowser.di.modules;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import jp.rei.andou.githubbrowser.di.scopes.WelcomeScope;
import jp.rei.andou.githubbrowser.presentation.authorization.SignInFragment;
import jp.rei.andou.githubbrowser.presentation.authorization.SignInViewModel;
import jp.rei.andou.githubbrowser.presentation.authorization.SignInViewModelImpl;
import jp.rei.andou.githubbrowser.presentation.general.ViewModelFactory;

@Module
public class WelcomeModule {

    @Provides
    @WelcomeScope
    public static SignInViewModel provideSignInViewModel(SignInFragment fragment,
                                                         ViewModelFactory factory) {
        return ViewModelProviders.of(fragment, factory).get(SignInViewModelImpl.class);
    }

}
