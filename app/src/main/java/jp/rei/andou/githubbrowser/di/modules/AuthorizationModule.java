package jp.rei.andou.githubbrowser.di.modules;

import dagger.Module;
import dagger.Provides;
import jp.rei.andou.githubbrowser.data.repositories.GithubRepository;
import jp.rei.andou.githubbrowser.data.repositories.SessionRepository;
import jp.rei.andou.githubbrowser.di.scopes.AuthorizationScope;
import jp.rei.andou.githubbrowser.domain.interactors.AuthorizationInteractor;
import jp.rei.andou.githubbrowser.domain.interactors.AuthorizationInteractorImpl;

@Module
public class AuthorizationModule {

    @Provides
    @AuthorizationScope
    public static AuthorizationInteractor provideInteractor(GithubRepository githubRepository,
                                                            SessionRepository sessionRepository) {
        return new AuthorizationInteractorImpl(githubRepository, sessionRepository);
    }

}
