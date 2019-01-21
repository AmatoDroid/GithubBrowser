package jp.rei.andou.githubbrowser.data.repositories;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPreferencesSessionRepository implements SessionRepository {

    private final SharedPreferences sharedPreferences;
    private final static String USER_CREDENTIAL_TAG = "USER_CREDENTIAL_TOKEN_TAG";

    @Inject
    public SharedPreferencesSessionRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }


    @Override
    public void saveUserCredentials(String credentials) {
        sharedPreferences.edit()
                .putString(USER_CREDENTIAL_TAG, credentials)
                .apply();
    }

    @Override
    public boolean isUserSessionAlive() {
        return sharedPreferences.contains(USER_CREDENTIAL_TAG);
    }
}
