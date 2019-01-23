package jp.rei.andou.githubbrowser.data.entities;

import lombok.Getter;
import lombok.Setter;

public enum NetworkState {

    RUNNING,
    SUCCESS,
    FAILED;

    @Setter
    @Getter
    private String message;

}
