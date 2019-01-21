package jp.rei.andou.githubbrowser.domain.interactors;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.Single;
import jp.rei.andou.githubbrowser.data.repositories.GithubRepository;
import jp.rei.andou.githubbrowser.data.repositories.SessionRepository;
import jp.rei.andou.githubbrowser.helpers.Base64Utils;
import okhttp3.ResponseBody;

public class AuthorizationInteractorImpl implements AuthorizationInteractor {

    private final SessionRepository sessionRepository;
    private final GithubRepository githubRepository;

    @Inject
    public AuthorizationInteractorImpl(GithubRepository githubRepository,
                                       SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
        this.githubRepository = githubRepository;
    }

    @Override
    public Single<ResponseBody> login(@NonNull String username, @NonNull String password) {
        String encodedCredentials = Base64Utils.encodeToBase64(username + ':' + password);
        return githubRepository.getAuthorizationUser(encodedCredentials)
                .flatMap(user -> {
                    sessionRepository.saveUserCredentials(encodedCredentials);
                    return Single.just(user);
                });
    }

    @Override
    public boolean validateUsername(CharSequence username) {
        return username.length() > 0;
    }

    @Override
    public boolean validatePassword(CharSequence password) {
        return password.length() > 0;
    }
}
