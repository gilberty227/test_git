package br.com.gitapp.domian.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("name") var name: String?,
    @SerializedName("full_name") var fullName: String?,
    @SerializedName("id") var id: Int?,
    @SerializedName("visibility") var visibility: String?,
    @SerializedName("language") var language: String?,
    @SerializedName("forks") var forks: Int?,
    @SerializedName("watchers") var watchers: Int?,
    @SerializedName("updated_at") var update: String?
)