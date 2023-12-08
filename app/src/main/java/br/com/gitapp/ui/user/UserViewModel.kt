package br.com.gitapp.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gitapp.domian.model.Repository
import br.com.gitapp.domian.model.User
import br.com.gitapp.domian.use_case.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val useCase: UserUseCase) : ViewModel() {

    val userInfo = MutableLiveData<User>()
    val listRepository = MutableLiveData<MutableList<Repository>>()

    fun getInfo(userName: String){
        getUserInfo(userName)
        getListRepository(userName)
    }


    private fun getUserInfo(userName: String) {
        CoroutineScope(Dispatchers.Main).launch{
            userInfo.postValue(useCase.getUserInfo(userName))
        }
    }

    private fun getListRepository(userName: String) {
        CoroutineScope(Dispatchers.Main).launch{
            listRepository.postValue(useCase.getListRepository(userName))
        }
    }
}