package com.example.bookappkotlin.screens.home.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookappkotlin.R
import com.example.bookappkotlin.repositories.database.UserViewModel
import com.example.bookappkotlin.databinding.ActivityHomeBinding
import com.example.bookappkotlin.repositories.helpper.AuthenticationHelper
import com.example.bookappkotlin.repositories.helpper.DatabaseAuthenticationHelper
import com.example.bookappkotlin.screens.home.services.HomeServices
import com.example.bookappkotlin.screens.home.adapters.PhotoAdapter
import com.example.bookappkotlin.screens.login.ui.LoginActivity
import com.example.bookappkotlin.screens.profile.ui.ProfileActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var viewModelUser: UserViewModel

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }

    lateinit var databaseAuthenticationHelper: AuthenticationHelper

    private val photoAdapter by inject<PhotoAdapter> {
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

        viewModelUser = ViewModelProvider(this).get(UserViewModel::class.java)

        databaseAuthenticationHelper = DatabaseAuthenticationHelper()
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
                R.id.item_1 -> startActivity(Intent(this@HomeActivity, ProfileActivity::class.java))
                R.id.item_2 -> Toast.makeText(
                    applicationContext,
                    "Clicked item 2", Toast.LENGTH_LONG
                ).show()
                R.id.item_3 -> {
                    service.signOutUser()
                    viewModelUser.deleteAllUsersData()
                    startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                }
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