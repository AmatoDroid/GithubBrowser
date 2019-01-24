package jp.rei.andou.githubbrowser.di;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.di.components.AppComponent;
import jp.rei.andou.githubbrowser.di.components.DaggerMainComponent;
import jp.rei.andou.githubbrowser.di.components.MainComponent;
import jp.rei.andou.githubbrowser.di.handlers.AuthorizationComponentHandler;
import jp.rei.andou.githubbrowser.di.handlers.BrowserComponentHandler;
import jp.rei.andou.githubbrowser.di.handlers.DestroyableComponent;
import jp.rei.andou.githubbrowser.presentation.authorization.SignInFragment;
import jp.rei.andou.githubbrowser.presentation.authorization.WelcomeFragment;
import jp.rei.andou.githubbrowser.presentation.browser.GithubBrowserFragment;
import jp.rei.andou.githubbrowser.presentation.main.MainActivity;

public class MainComponentHandler {

    private final AppComponent appComponent;
    private MainComponent mainComponent;
    private Map<Class<? extends DestroyableComponent>, DestroyableComponent> componentMap = new HashMap<>();

    @Inject
    public MainComponentHandler(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    public void inject(MainActivity activity) {
        if (mainComponent == null) {
            mainComponent = DaggerMainComponent.builder()
                    .withAppComponent(appComponent)
                    .bindMainActivity(activity)
                    .build();
        }
        mainComponent.inject(activity);
    }

    public AuthorizationComponentHandler getOrCreateAuthComponentHandler() {
        checkMainComponent();
        AuthorizationComponentHandler handler = (AuthorizationComponentHandler) componentMap.get(
                AuthorizationComponentHandler.class
        );
        if (handler == null) {
            handler = new AuthorizationComponentHandler(mainComponent);
            componentMap.put(AuthorizationComponentHandler.class, handler);
        }
        return handler;
    }

    public BrowserComponentHandler getOrCreateBrowserComponentHandler() {
       checkMainComponent();
       BrowserComponentHandler handler = (BrowserComponentHandler) componentMap.get(
               BrowserComponentHandler.class
       );
       if (handler == null) {
           handler = new BrowserComponentHandler(mainComponent);
           componentMap.put(BrowserComponentHandler.class, handler);
       }
       return handler;
    }

    private void checkMainComponent() {
        if (mainComponent == null) {
            throw new IllegalStateException("MainComponent is null. Unable to create dependency component");
        }
    }

    public void inject(SignInFragment fragment) {
        getOrCreateAuthComponentHandler().inject(fragment);
    }

    public void inject(WelcomeFragment fragment) {
        getOrCreateAuthComponentHandler().inject(fragment);
    }

    public void inject(GithubBrowserFragment fragment) {
        getOrCreateBrowserComponentHandler().inject(fragment);
    }

    public void destroy(GithubBrowserFragment fragment) {
        getOrCreateBrowserComponentHandler().destroy();
    }

    public void destroy(SignInFragment fragment) {
        getOrCreateAuthComponentHandler().destroy(fragment);
    }

    public void destroy(WelcomeFragment fragment) {
        getOrCreateAuthComponentHandler().destroy(fragment);
    }


    public void destroy(MainActivity activity) {
        for (DestroyableComponent component : componentMap.values()) {
            component.destroy();
        }
        componentMap.clear();
        mainComponent = null;
    }

}
