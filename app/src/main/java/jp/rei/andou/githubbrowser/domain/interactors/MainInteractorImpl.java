package jp.rei.andou.githubbrowser.domain.interactors;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.data.repositories.SessionRepository;

public class MainInteractorImpl implements MainInteractor {

    private final SessionRepository sessionRepository;

    @Inject
    public MainInteractorImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public boolean isUserSessionAlive() {
        return sessionRepository.isUserSessionAlive();
    }
}
