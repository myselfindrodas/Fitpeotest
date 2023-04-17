package com.example.fitpeotest.ui

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fitpeotest.R
import com.example.fitpeotest.data.ApiClient
import com.example.fitpeotest.data.ApiHelper
import com.example.fitpeotest.data.model.PhotoResponseModelItem
import com.example.fitpeotest.data.modelfactory.CommonModelFactory
import com.example.fitpeotest.databinding.ActivityMainBinding
import com.example.fitpeotest.ui.adapter.PhotoListAdapter
import com.example.fitpeotest.utils.Status
import com.example.fitpeotest.utils.Utilities
import com.example.fitpeotest.viewmodel.CommonViewModel

class MainActivity : AppCompatActivity(), PhotoListAdapter.OnItemClickListener {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CommonViewModel
    private var photoListAdapter:PhotoListAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(binding) {
            val vm: CommonViewModel by viewModels {
                CommonModelFactory(ApiHelper(ApiClient.apiService))
            }
            viewModel = vm

            photoListAdapter = PhotoListAdapter(this@MainActivity, this@MainActivity)
            rvList.adapter = photoListAdapter
            rvList.layoutManager = GridLayoutManager(this@MainActivity, 1)
            rvList()


        }
    }



    private fun rvList(){

        if (Utilities.isNetworkAvailable(this)) {

            viewModel.photolist().observe(this) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()
                            resource.data?.let { itProfileInfo ->

                                photoListAdapter?.updateData(itProfileInfo)

                            }

                        }
                        Status.ERROR -> {
                            hideProgressDialog()
                            Log.d(ContentValues.TAG, "print-->" + resource.data)

                        }

                        Status.LOADING -> {
                            showProgressDialog()
                        }

                    }

                }
            }

        }else{

            Toast.makeText(this, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(
        position: Int,
        view: View,
        id: String,
        s: String,
        mPhotoListModelArrayList: ArrayList<PhotoResponseModelItem>
    ) {

        val intent = Intent(this@MainActivity, Details::class.java)
        intent.putExtra("title", mPhotoListModelArrayList[position].title)
        intent.putExtra("url", mPhotoListModelArrayList[position].url)
        startActivity(intent)

    }


    fun showProgressDialog() {
        binding.rlLoading.visibility = View.VISIBLE
    }

    fun hideProgressDialog() {
        binding.rlLoading.visibility = View.GONE
    }

}