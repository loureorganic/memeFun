package com.example.bookappkotlin.home.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookappkotlin.R
import com.example.bookappkotlin.databinding.ActivityHomeBinding
import com.example.bookappkotlin.home.services.HomeServices
import com.example.bookappkotlin.home.utils.PhotoAdapter
import com.example.bookappkotlin.login.ui.LoginActivity
import com.example.bookappkotlin.profile.ProfileActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    private val recyclerView: RecyclerView by lazy {
      findViewById(R.id.recyclerView)
}

    private val photoAdapter by inject<PhotoAdapter>() {
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

        toggle = ActionBarDrawerToggle(this, binding.drawerLayoutOne, R.string.open, R.string.close)
        binding.drawerLayoutOne.addDrawerListener(toggle)
        toggle.syncState()

        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.adapter = photoAdapter

        getMyData()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navigationView.bringToFront()

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                Log.d("OI", "OII"),
                R.id.item_1 -> startActivity(Intent(this@HomeActivity, ProfileActivity::class.java))
                R.id.item_2 -> Toast.makeText(applicationContext,
                    "Clicked item 2", Toast.LENGTH_LONG).show()
                R.id.item_3 -> startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
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
                Log.i("TEST", "RETURN GET DATA$response")
            }.run { composite.add(this) }
    }

    override fun onDestroy() {
        composite.clear()
        super.onDestroy()
    }
}