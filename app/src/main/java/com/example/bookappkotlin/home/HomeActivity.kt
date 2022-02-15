package com.example.bookappkotlin.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookappkotlin.R
import com.example.bookappkotlin.databinding.ActivityHomeBinding
import com.example.bookappkotlin.home.services.HomeServices
import com.example.bookappkotlin.login.services.LoginService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity() {

    //dependency
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }

    private val photoAdapter by inject<PhotoAdapter>(){
        parametersOf(applicationContext)
    }

    private val service by inject<HomeServices>()


    private lateinit var binding: ActivityHomeBinding

    //dependency
    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)
        recyclerView.adapter = photoAdapter
        getMyData()
    }

    @SuppressLint("NotifyDataSetChanged")
    // can be upgraded
    private fun getMyData() {

        service
            .getAllMemes()
            .subscribeOn(Schedulers.io())
            .filter { it.data?.meme != null }
            .doOnError { error ->
                error.printStackTrace()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                response?.data?.meme?.let { memeList ->
                    photoAdapter.setDataList(memeList)
                    photoAdapter.notifyDataSetChanged()
                }
            }.run { composite.add(this) }
    }

    override fun onDestroy() {
        composite.clear()
        super.onDestroy()
    }
}