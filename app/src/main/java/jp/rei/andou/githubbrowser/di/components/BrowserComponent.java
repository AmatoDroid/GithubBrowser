package jp.rei.andou.githubbrowser.di.components;

import dagger.BindsInstance;
import dagger.Component;
import jp.rei.andou.githubbrowser.di.modules.BrowserModule;
import jp.rei.andou.githubbrowser.di.scopes.BrowserScope;
import jp.rei.andou.githubbrowser.presentation.browser.GithubBrowserFragment;

@Component(dependencies = MainComponent.class, modules = BrowserModule.class)
@BrowserScope
public interface BrowserComponent {

    void inject(GithubBrowserFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder bindFragment(GithubBrowserFragment fragment);
        Builder withMainComponent(MainComponent component);
        BrowserComponent build();
    }
}
