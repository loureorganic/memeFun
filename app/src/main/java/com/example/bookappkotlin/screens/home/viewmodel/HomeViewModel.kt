package com.example.bookappkotlin.screens.home.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookappkotlin.screens.home.model.Meme
import com.example.bookappkotlin.screens.home.services.HomeServices
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ViewModelHome {
    val listMemeResponseLiveData: MutableLiveData<List<Meme>>
    val errorMemeResponseLiveData: MutableLiveData<Boolean>
    fun getAllMemes()
    fun signOutUser()
}

class HomeViewModel : ViewModel(), KoinComponent, ViewModelHome {

    private val services by inject<HomeServices>()

    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private val memeResponseLiveData = MutableLiveData<List<Meme>>()
    override val listMemeResponseLiveData: MutableLiveData<List<Meme>> = memeResponseLiveData

    private val errorMemeResponse = MutableLiveData<Boolean>()
    override val errorMemeResponseLiveData: MutableLiveData<Boolean> = errorMemeResponse

    @SuppressLint("CheckResult")
    override fun getAllMemes() {

        services.getAllMemes().subscribe({ response ->
            response?.data?.meme?.let { memeList ->
                errorMemeResponseLiveData.postValue(false)
                listMemeResponseLiveData.postValue(memeList)
            }
        }, { e ->
                errorMemeResponseLiveData.postValue(true)
                Log.i("ERROR", "getMemes ${e.message}")
            })
            .run { composite.add(this) }
    }

    override fun signOutUser() {
        return services.signOutUser()
    }

    override fun onCleared() {
        composite.clear()
        super.onCleared()
    }
}