package jp.rei.andou.githubbrowser.di.modules;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import jp.rei.andou.githubbrowser.data.repositories.AuthorizationRepository;
import jp.rei.andou.githubbrowser.data.repositories.AuthorizationRepositoryNetwork;
import jp.rei.andou.githubbrowser.di.scopes.AuthorizationScope;
import jp.rei.andou.githubbrowser.domain.interactors.AuthorizationInteractor;
import jp.rei.andou.githubbrowser.domain.interactors.AuthorizationInteractorImpl;
import jp.rei.andou.githubbrowser.presentation.authorization.AuthorizationNavigator;
import jp.rei.andou.githubbrowser.presentation.authorization.AuthorizationRouter;

@Module
public class AuthorizationModule {

    @Provides
    @AuthorizationScope
    public static AuthorizationRepository provideRepository() {
        return new AuthorizationRepositoryNetwork();
    }

    @Provides
    @AuthorizationScope
    public static AuthorizationInteractor provideInteractor(AuthorizationRepository repository) {
        return new AuthorizationInteractorImpl(repository);
    }

    @Provides
    @AuthorizationScope
    public static AuthorizationNavigator provideAuthorizationNavigator(FragmentManager fragmentManager) {
        return new AuthorizationRouter(fragmentManager);
    }

}
