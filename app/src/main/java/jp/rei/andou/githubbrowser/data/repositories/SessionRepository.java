package jp.rei.andou.githubbrowser.data.repositories;

public interface SessionRepository {

    void saveUserCredentials(String credentials);

    boolean isUserSessionAlive();

}
