package jp.rei.andou.githubbrowser.data.repositories;

import java.util.List;

import io.reactivex.Single;
import jp.rei.andou.githubbrowser.data.entities.Repo;
import okhttp3.ResponseBody;

public interface GithubRepository  {

    Single<ResponseBody> getAuthorizationUser(String userCredential);

    Single<List<Repo>> searchRepositoriesByQuery(
            String userCredential, String query, Long pageNumber, Integer perPage
    );

}
