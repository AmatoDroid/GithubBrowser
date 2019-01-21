package jp.rei.andou.githubbrowser.data.repositories;

import io.reactivex.Single;
import okhttp3.ResponseBody;

public interface GithubRepository  {

    Single<ResponseBody> getAuthorizationUser(String userCredential);

    Single<ResponseBody> searchRepositoriesByQuery(
            String userCredential, String query, Integer pageNumber, Integer perPage
    );

}
