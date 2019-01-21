package jp.rei.andou.githubbrowser.domain.interactors;

import io.reactivex.Single;
import okhttp3.ResponseBody;

public interface AuthorizationInteractor {

    Single<ResponseBody> login(String username, String password);

    boolean validateUsername(CharSequence username);

    boolean validatePassword(CharSequence password);
}
