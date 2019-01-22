package jp.rei.andou.githubbrowser.presentation.browser;

import com.jakewharton.rxrelay2.PublishRelay;

public interface BrowserViewModel {

    PublishRelay<String> getSearchQuerySubject();
}
