package jp.rei.andou.githubbrowser.presentation.authorization.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationUser {

    private String username;
    private String password;

    public boolean isEmpty() {
        return username == null || password == null || username.isEmpty() || password.isEmpty();
    }
}
