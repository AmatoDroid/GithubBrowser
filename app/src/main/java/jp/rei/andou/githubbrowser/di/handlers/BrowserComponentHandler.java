package jp.rei.andou.githubbrowser.di.handlers;

import jp.rei.andou.githubbrowser.di.components.BrowserComponent;
import jp.rei.andou.githubbrowser.di.components.MainComponent;
import jp.rei.andou.githubbrowser.presentation.browser.GithubBrowserFragment;

public class BrowserComponentHandler implements DestroyableComponent {

    private final MainComponent mainComponent;
    private BrowserComponent browserComponent;

    public BrowserComponentHandler(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }

    public void inject(GithubBrowserFragment fragment) {
//        browserComponent = DaggerBrowserComponent.builder()
    }

    @Override
    public void destroy() {

    }
}
