package jp.rei.andou.githubbrowser.data.repositories;

import android.support.annotation.Nullable;

public interface SessionRepository {

    void saveUserCredentials(String credentials);

    boolean isUserSessionAlive();

    @Nullable
    String getUserCredentials();

    void logout();
}
