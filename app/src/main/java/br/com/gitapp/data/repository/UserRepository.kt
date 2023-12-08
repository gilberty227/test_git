package br.com.gitapp.data.repository

import androidx.lifecycle.MutableLiveData
import br.com.gitapp.data.api.ApiGithub
import br.com.gitapp.domian.model.Repository
import br.com.gitapp.domian.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor (private var apiService: ApiGithub){


    suspend fun getListUser(): MutableList<User>? {
        return withContext(Dispatchers.IO) {
             apiService.getListUser().body()?.toMutableList()
        }
    }

    suspend fun getUserInfo(user: String): User? {
        return withContext(Dispatchers.IO) {
            apiService.getInfoUser(user).body()
        }
    }

    suspend fun getListRepository(user: String): MutableList<Repository>? {
        return withContext(Dispatchers.IO) {
            apiService.getListRepository(user).body()
        }
    }
}