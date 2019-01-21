package jp.rei.andou.githubbrowser.data.network;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GithubRestService {

    @GET("user")
    Single<ResponseBody> getAuthorizationUser(@Header("Authorization") String userCredential);

    @GET("search/repositories")
    Single<ResponseBody> searchRepositoriesByQuery(
            @Header("Authorization") String userCredential,
            @Query("q") String query,
            @Query("page") Integer pageNumber,
            @Query("per_page") Integer perPage
    );

}
