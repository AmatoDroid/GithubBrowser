package jp.rei.andou.githubbrowser.di.components;

import dagger.Component;
import jp.rei.andou.githubbrowser.data.repositories.GithubRepository;
import jp.rei.andou.githubbrowser.data.repositories.SessionRepository;
import jp.rei.andou.githubbrowser.di.modules.MainModule;
import jp.rei.andou.githubbrowser.di.scopes.MainScope;
import jp.rei.andou.githubbrowser.presentation.main.MainActivity;

@Component(dependencies = AppComponent.class, modules = {MainModule.class})
@MainScope
public interface MainComponent {

    void inject(MainActivity mainActivity);
    GithubRepository githubRepository();
    SessionRepository sessionRepository();

    @Component.Builder
    interface Builder {
        Builder withAppComponent(AppComponent appComponent);
        MainComponent build();
    }

}
