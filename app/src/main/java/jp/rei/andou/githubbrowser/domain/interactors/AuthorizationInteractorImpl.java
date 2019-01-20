package jp.rei.andou.githubbrowser.domain.interactors;

import javax.inject.Inject;

import io.reactivex.Single;
import jp.rei.andou.githubbrowser.data.repositories.AuthorizationRepository;
import okhttp3.RequestBody;

public class AuthorizationInteractorImpl implements AuthorizationInteractor {

    private final AuthorizationRepository repository;

    @Inject
    public AuthorizationInteractorImpl(AuthorizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<RequestBody> login(String username, String password) {
        return null;
    }
}
