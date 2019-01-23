package jp.rei.andou.githubbrowser.data.entities;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class Owner {

    @SerializedName("avatar_url")
    private String avatarUrl;

}
