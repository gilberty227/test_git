package br.com.gitapp.domian.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") var login: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("node_id") var nodeId: String? = null,
    @SerializedName("avatar_url") var avatarUrl: String? = null,
    @SerializedName("gravatar_id") var gravatarId: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("site_admin") var siteAdmin: Boolean? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("company") var company: String? = null,
    @SerializedName("blog") var blog: String? = null,
    @SerializedName("location") var location: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("hireable") var hireable: String? = null,
    @SerializedName("bio") var bio: String? = null,
    @SerializedName("followers") var followers: Int? = null,
    @SerializedName("following") var following: Int? = null,
    @SerializedName("created_at")var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)
