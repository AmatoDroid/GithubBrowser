package jp.rei.andou.githubbrowser.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

@Getter
public class RepoList {

    @SerializedName("items")
    private List<Repo> repositories;

}
