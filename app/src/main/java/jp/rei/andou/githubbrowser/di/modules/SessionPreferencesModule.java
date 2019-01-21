package jp.rei.andou.githubbrowser.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.rei.andou.githubbrowser.data.repositories.SessionRepository;
import jp.rei.andou.githubbrowser.data.repositories.SharedPreferencesSessionRepository;

@Module
public class SessionPreferencesModule {

    private final static String SHARED_REFERENCES_TAG = "GENERAL_SHARED_PREFERENCES_TAG";

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context applicationContext) {
        return applicationContext.getSharedPreferences(SHARED_REFERENCES_TAG, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public SessionRepository provideSessionRepository(SharedPreferences sharedPreferences) {
        return new SharedPreferencesSessionRepository(sharedPreferences);
    }

}
