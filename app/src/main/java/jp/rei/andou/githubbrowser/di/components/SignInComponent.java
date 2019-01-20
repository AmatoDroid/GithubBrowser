package jp.rei.andou.githubbrowser.di.components;

import dagger.BindsInstance;
import dagger.Subcomponent;
import jp.rei.andou.githubbrowser.di.modules.SignInModule;
import jp.rei.andou.githubbrowser.di.scopes.SignInScope;
import jp.rei.andou.githubbrowser.presentation.authorization.SignInFragment;

@Subcomponent(modules = {SignInModule.class})
@SignInScope
public interface SignInComponent {

    void inject(SignInFragment fragment);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder bindFragment(SignInFragment fragment);
        SignInComponent build();
    }

}
