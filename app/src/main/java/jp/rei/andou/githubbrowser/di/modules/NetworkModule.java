package jp.rei.andou.githubbrowser.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jp.rei.andou.githubbrowser.BuildConfig;
import jp.rei.andou.githubbrowser.data.network.GithubRestService;
import jp.rei.andou.githubbrowser.data.repositories.GithubRepository;
import jp.rei.andou.githubbrowser.data.repositories.GithubRepositoryNetwork;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClient)
                .build();
    }

    @Provides
    @Singleton
    public static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    public static GithubRestService provideGithubRestService(Retrofit retrofit) {
        return retrofit.create(GithubRestService.class);
    }

    @Provides
    @Singleton
    public static GithubRepository provideGithubRepository(GithubRestService githubRestService) {
        return new GithubRepositoryNetwork(githubRestService);
    }

}
