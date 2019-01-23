package jp.rei.andou.githubbrowser.data.DataSources;

import android.arch.paging.DataSource;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.data.entities.Repo;
import jp.rei.andou.githubbrowser.data.repositories.GithubRepository;
import jp.rei.andou.githubbrowser.data.repositories.SessionRepository;
import lombok.Setter;

public class RepositoryDataFactory extends DataSource.Factory<Long, Repo> {

    private final GithubRepository githubRepository;
    private final SessionRepository sessionRepository;
    @Setter
    private String query = null;

    @Inject
    public RepositoryDataFactory(GithubRepository githubRepository,
                                 SessionRepository sessionRepository) {
        this.githubRepository = githubRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public DataSource<Long, Repo> create() {
        return new RepositoryDataSource(query, githubRepository, sessionRepository);
    }

}
