package jp.rei.andou.githubbrowser.di.handlers;

import jp.rei.andou.githubbrowser.di.components.AuthorizationComponent;
import jp.rei.andou.githubbrowser.di.components.DaggerAuthorizationComponent;
import jp.rei.andou.githubbrowser.di.components.MainComponent;
import jp.rei.andou.githubbrowser.di.components.SignInComponent;
import jp.rei.andou.githubbrowser.di.components.WelcomeComponent;
import jp.rei.andou.githubbrowser.presentation.authorization.SignInFragment;
import jp.rei.andou.githubbrowser.presentation.authorization.WelcomeFragment;

public class AuthorizationComponentHandler implements DestroyableComponent {

    private final MainComponent mainComponent;
    private AuthorizationComponent authorizationComponent;
    private WelcomeComponent welcomeComponent;
    private SignInComponent signInComponent;

    public AuthorizationComponentHandler(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }

    private void createAuthorizationComponentIfNeeded() {
        if (authorizationComponent != null) {
            return;
        }
        authorizationComponent = DaggerAuthorizationComponent.builder()
                .mainComponent(mainComponent)
                .build();
    }

    public void inject(SignInFragment fragment) {
        createAuthorizationComponentIfNeeded();
        signInComponent = authorizationComponent.getSignInComponentBuilder()
                .bindFragment(fragment)
                .build();
        signInComponent.inject(fragment);
    }

    public void inject(WelcomeFragment fragment) {
        createAuthorizationComponentIfNeeded();
        welcomeComponent = authorizationComponent.getWelcomeComponentBuilder()
                .bindFragment(fragment)
                .build();
        welcomeComponent.inject(fragment);
    }

    public void destroySignInComponent() {
        signInComponent = null;
    }

    public void destroyWelcomeComponent() {
        welcomeComponent = null;
    }

    @Override
    public void destroy() {
        authorizationComponent = null;
        welcomeComponent = null;
        signInComponent = null;
    }
}
