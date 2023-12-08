package br.com.gitapp.domian.use_case

import androidx.lifecycle.MutableLiveData
import br.com.gitapp.data.repository.UserRepository
import br.com.gitapp.domian.model.Repository
import br.com.gitapp.domian.model.User
import javax.inject.Inject

class UserUseCase @Inject constructor(private var repository: UserRepository){

    suspend fun getListUser(): MutableList<User>? {
        return repository.getListUser()
    }

    suspend fun getUserInfo(userName: String): User? {
        return repository.getUserInfo(userName)
    }

    suspend fun getListRepository(userName: String): MutableList<Repository>? {
        return repository.getListRepository(userName)
    }
}