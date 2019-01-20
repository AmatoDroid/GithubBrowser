package jp.rei.andou.githubbrowser.presentation.authorization;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.domain.interactors.AuthorizationInteractor;
import jp.rei.andou.githubbrowser.presentation.authorization.model.AuthorizationUser;
import lombok.Getter;
import lombok.Setter;

public class SignInViewModelImpl extends ViewModel implements SignInViewModel {

    private final AuthorizationInteractor interactor;

    @Inject
    public SignInViewModelImpl(AuthorizationInteractor interactor) {
        this.interactor = interactor;
    }

    @Getter
    private final AuthorizationUser user = new AuthorizationUser();

    @Override
    public void login() {

    }
}
