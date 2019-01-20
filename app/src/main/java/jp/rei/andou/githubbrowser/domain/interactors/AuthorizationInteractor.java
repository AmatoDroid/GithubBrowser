package jp.rei.andou.githubbrowser.domain.interactors;

import io.reactivex.Single;
import okhttp3.RequestBody;

public interface AuthorizationInteractor {

    Single<RequestBody> login(String username, String password);

}
