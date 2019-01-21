package jp.rei.andou.githubbrowser.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import jp.rei.andou.githubbrowser.App;
import jp.rei.andou.githubbrowser.data.repositories.GithubRepository;
import jp.rei.andou.githubbrowser.data.repositories.SessionRepository;
import jp.rei.andou.githubbrowser.di.modules.NetworkModule;
import jp.rei.andou.githubbrowser.di.modules.SessionPreferencesModule;

@Component(modules = {NetworkModule.class, SessionPreferencesModule.class})
@Singleton
public interface AppComponent {

    void inject(App application);

    GithubRepository githubRepository();
    SessionRepository sessionRepository();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder bindContext(Context applicationContext);
        AppComponent build();
    }
}
