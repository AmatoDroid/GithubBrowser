package jp.rei.andou.githubbrowser.data.repositories;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import jp.rei.andou.githubbrowser.data.entities.Repo;
import jp.rei.andou.githubbrowser.data.entities.RepoList;
import jp.rei.andou.githubbrowser.data.network.GithubRestService;
import jp.rei.andou.githubbrowser.helpers.SchedulersTransformer;
import okhttp3.ResponseBody;

public class GithubRepositoryNetwork implements GithubRepository {

    private final GithubRestService githubService;

    @Inject
    public GithubRepositoryNetwork(GithubRestService service) {
        this.githubService = service;
    }

    @Override
    public Single<ResponseBody> getAuthorizationUser(String userCredential) {
        return githubService.getAuthorizationUser(userCredential)
                .compose(SchedulersTransformer.create());
    }

    @Override
    public Single<List<Repo>> searchRepositoriesByQuery(String userCredential, String query,
                                                        Long pageNumber, Integer perPage) {
        return githubService.searchRepositoriesByQuery(userCredential, query, pageNumber, perPage)
                .compose(SchedulersTransformer.create())
                .map(RepoList::getRepositories);
    }
}
