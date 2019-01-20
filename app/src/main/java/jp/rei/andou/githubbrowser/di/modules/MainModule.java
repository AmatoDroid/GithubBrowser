package jp.rei.andou.githubbrowser.di.modules;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import jp.rei.andou.githubbrowser.di.scopes.MainScope;
import jp.rei.andou.githubbrowser.presentation.MainActivity;
import jp.rei.andou.githubbrowser.presentation.general.GeneralNavigator;
import jp.rei.andou.githubbrowser.presentation.general.GeneralRouter;

@Module
public class MainModule {

    @Provides
    @MainScope
    public static FragmentManager provideFragmentManager(MainActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @Provides
    @MainScope
    public static GeneralNavigator provideGeneralNavigator(FragmentManager fragmentManager) {
        return new GeneralRouter(fragmentManager);
    }

}
