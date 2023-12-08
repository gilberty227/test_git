package br.com.gitapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gitapp.data.repository.UserRepository
import br.com.gitapp.domian.model.User
import br.com.gitapp.domian.use_case.UserUseCase
import br.com.gitapp.domian.util.ConnectivityInterceptor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: UserUseCase,
    val internet: ConnectivityInterceptor
): ViewModel() {

    val listUser = MutableLiveData<MutableList<User>>()

    init {
        checkInternet()
    }

    fun checkInternet() {
        internet.updateConnection()
    }

    fun getListUser() {
        CoroutineScope(Dispatchers.Main).launch{
            listUser.postValue(useCase.getListUser())
        }
    }
}