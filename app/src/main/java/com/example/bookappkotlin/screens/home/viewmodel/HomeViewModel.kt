package com.example.bookappkotlin.screens.home.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookappkotlin.screens.home.model.Meme
import com.example.bookappkotlin.screens.home.model.MemeResponse
import com.example.bookappkotlin.screens.home.services.HomeServices
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ViewModelHome{
    val listMemeResponseLiveData : MutableLiveData<List<Meme>>
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

    @SuppressLint("CheckResult")
    override fun getAllMemes() {
        var result = services.getAllMemes()
        result.subscribe { response ->
            response?.data?.meme?.let { memeList ->
                Log.i("MEME LIST", "TESTE" + memeList)
                listMemeResponseLiveData.postValue(memeList)
            }
        }.run { composite.add(this) }
    }

    override fun signOutUser() {
        return services.signOutUser()
    }
}