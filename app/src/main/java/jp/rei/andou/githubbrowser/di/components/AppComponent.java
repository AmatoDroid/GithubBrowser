package jp.rei.andou.githubbrowser.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import jp.rei.andou.githubbrowser.App;
import jp.rei.andou.githubbrowser.di.modules.NetworkModule;

@Component(modules= {NetworkModule.class})
@Singleton
public interface AppComponent {

    void inject(App application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder bindContext(Context applicationContext);
        AppComponent build();
    }
}
