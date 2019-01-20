package jp.rei.andou.githubbrowser.di.components;

import dagger.Component;
import jp.rei.andou.githubbrowser.di.modules.AuthorizationModule;
import jp.rei.andou.githubbrowser.di.scopes.AuthorizationScope;

@AuthorizationScope
@Component(
        dependencies = MainComponent.class,
        modules = {AuthorizationModule.class}
)
public interface AuthorizationComponent {

    WelcomeComponent.Builder getWelcomeComponentBuilder();
    SignInComponent.Builder getSignInComponentBuilder();

}
