package br.com.gitapp.domian.model

import com.google.gson.annotations.SerializedName

object FakeUserData {
    val listUser: MutableList<User> = mutableListOf(
        User(
            login = "luiz",
            id = "1",
            nodeId = "MDQ6VXNlcjE=",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            gravatarId = "",
            type = "User",
            siteAdmin = false),
        User(
            login = "mojombo",
            id = "2",
            nodeId = "MDQ6VXNlcjE=",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            gravatarId = "",
            type = "User",
            siteAdmin = false),
        User(
            login = "Gilberto",
            id = "3",
            nodeId = "MDQ6VXNlcjE=",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            gravatarId = "",
            type = "User",
            siteAdmin = false),
        User(
            login = "Michele",
            id = "4",
            nodeId = "MDQ6VXNlcjE=",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            gravatarId = "",
            type = "User",
            siteAdmin = false),
    )
}
