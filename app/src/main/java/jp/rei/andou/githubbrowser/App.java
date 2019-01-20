package jp.rei.andou.githubbrowser;

import android.app.Application;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.di.components.AppComponent;
import jp.rei.andou.githubbrowser.di.MainComponentHandler;
import jp.rei.andou.githubbrowser.di.components.DaggerAppComponent;
import lombok.experimental.Delegate;

public class App extends Application {

    private AppComponent appComponent;

    @Inject
    @Delegate
    MainComponentHandler mainComponentHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildComponent();
        appComponent.inject(this);
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder().bindContext(this).build();
    }
}
