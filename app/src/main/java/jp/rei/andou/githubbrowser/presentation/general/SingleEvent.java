package jp.rei.andou.githubbrowser.presentation.general;

import lombok.Getter;

public class SingleEvent<T> {

    private final T content;

    public SingleEvent(T content) {
        this.content = content;
    }

    @Getter
    private boolean hasBeenHandled = false;

    public T getContent() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }

    public T getContentAnyway() {
        return content;
    }
}