package jp.rei.andou.githubbrowser.di.components;

import dagger.BindsInstance;
import dagger.Subcomponent;
import jp.rei.andou.githubbrowser.di.modules.WelcomeModule;
import jp.rei.andou.githubbrowser.presentation.authorization.WelcomeFragment;

@Subcomponent(modules = {WelcomeModule.class})
public interface WelcomeComponent {

    void inject(WelcomeFragment fragment);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder bindFragment(WelcomeFragment fragment);
        WelcomeComponent build();
    }

}

