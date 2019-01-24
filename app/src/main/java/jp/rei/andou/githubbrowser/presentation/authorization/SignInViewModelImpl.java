package jp.rei.andou.githubbrowser.presentation.authorization;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.net.HttpURLConnection;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import jp.rei.andou.githubbrowser.R;
import jp.rei.andou.githubbrowser.domain.interactors.AuthorizationInteractor;
import jp.rei.andou.githubbrowser.presentation.authorization.model.AuthorizationUser;
import jp.rei.andou.githubbrowser.presentation.general.Navigation;
import jp.rei.andou.githubbrowser.presentation.general.SingleEvent;
import lombok.Getter;
import retrofit2.HttpException;

public class SignInViewModelImpl extends ViewModel implements SignInViewModel {

    private final AuthorizationInteractor interactor;
    @Getter
    private final AuthorizationUser user = new AuthorizationUser();
    @Getter
    private MutableLiveData<String> usernameFieldError = new MutableLiveData<>();
    @Getter
    private MutableLiveData<String> passwordFieldError = new MutableLiveData<>();
    @Getter
    private MutableLiveData<SingleEvent<Integer>> toastErrorMessage = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<SingleEvent<Navigation>> navigation = new MutableLiveData<>();

    @Inject
    public SignInViewModelImpl(AuthorizationInteractor interactor) {
        this.interactor = interactor;
        //for initial button disabling
        usernameFieldError.postValue("");
        passwordFieldError.postValue("");
    }

    @Override
    public void login() {
        if (user.isEmpty()) {
            return;
        }
        Disposable disposable = interactor.login(user.getUsername(), user.getPassword())
                .subscribe(user -> navigation.postValue(new SingleEvent<>(Navigation.BROWSER)),
                        throwable -> {
                            int errorStringRes;
                            if (throwable instanceof HttpException) {
                                HttpException httpException = (HttpException) throwable;
                                int statusCode = httpException.code();
                                if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                                    errorStringRes = R.string.credentials_is_invalid;
                                } else {
                                    errorStringRes = R.string.server_error;
                                }
                            } else {
                                errorStringRes = R.string.internal_error;
                            }
                            toastErrorMessage.postValue(new SingleEvent<>(errorStringRes));
                        });
        compositeDisposable.add(disposable);
    }

    @Override
    public LiveData<SingleEvent<Integer>> getErrorToastMessages() {
        return toastErrorMessage;
    }

    @Override
    public LiveData<SingleEvent<Navigation>> getNavigationEvents() {
        return navigation;
    }

    public void checkUsername(CharSequence username) {
        String errorText = null;
        if (!interactor.validateUsername(username)) {
            errorText = "Username is invalid";
        }
        if (!Objects.equals(usernameFieldError.getValue(), errorText)) {
            usernameFieldError.postValue(errorText);
        }
    }

    public void checkPassword(CharSequence password) {
        String errorText = null;
        if (!interactor.validatePassword(password)) {
            errorText = "Password is invalid";
        }
        if (!Objects.equals(passwordFieldError.getValue(), errorText)) {
            passwordFieldError.postValue(errorText);
        }
    }
}
