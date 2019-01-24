package jp.rei.andou.githubbrowser.di.modules;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import jp.rei.andou.githubbrowser.data.repositories.SessionRepository;
import jp.rei.andou.githubbrowser.di.modules.factories.MainViewModelFactoryModule;
import jp.rei.andou.githubbrowser.di.scopes.MainScope;
import jp.rei.andou.githubbrowser.domain.interactors.MainInteractor;
import jp.rei.andou.githubbrowser.domain.interactors.MainInteractorImpl;
import jp.rei.andou.githubbrowser.presentation.general.GeneralNavigator;
import jp.rei.andou.githubbrowser.presentation.general.GeneralRouter;
import jp.rei.andou.githubbrowser.presentation.main.MainActivity;

@Module(includes = MainViewModelFactoryModule.class)
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

    @Provides
    @MainScope
    public static MainInteractor provideMainInteractor(SessionRepository sessionRepository) {
        return new MainInteractorImpl(sessionRepository);
    }

}
