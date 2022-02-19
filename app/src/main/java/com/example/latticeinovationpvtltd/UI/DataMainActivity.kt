package com.example.latticeinovationpvtltd.UI

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latticeinovationpvtltd.API.NetworkHelperClass
import com.example.latticeinovationpvtltd.DATA.models.Article
import com.example.latticeinovationpvtltd.R
import com.example.latticeinovationpvtltd.UI.Adapter.ResponseAdapter
import com.example.latticeinovationpvtltd.UI.ViewModel.ResponseViewModel
import com.example.latticeinovationpvtltd.databinding.ActivityMainDataBinding
import java.util.*
import kotlin.collections.ArrayList


class DataMainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainDataBinding
    var list = emptyList<Article>()
    lateinit var responseAdapter : ResponseAdapter
    private lateinit var responseViewModel: ResponseViewModel
    private var tempList = mutableListOf<Article>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        responseAdapter = ResponseAdapter(this, tempList)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_data)

        responseViewModel = ViewModelProvider(this)[ResponseViewModel::class.java]
        /**
         * starting the shimmer effect
         */
        binding.shimmerFrameLayout.startShimmerAnimation()
        loadData()
    }

    /**
     * setting all the data in recyclerview
     */
    private fun setAdapter() {
        responseAdapter = ResponseAdapter(this,list)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            adapter = responseAdapter
            layoutManager = linearLayoutManager
        }
    }
    /**
     * observing each data inside the list using live data
     */
    private fun loadData(){
        responseViewModel.callAPI()
        responseViewModel.liveData.observe(this) {
            it.let {
                when (it) {
                    is NetworkHelperClass.OnSuccess_1 -> {
                        /**
                         * stopping shimmer effect, setting visibility gone for shimmer layout and setting the visibility
                         * of recycler view to show data
                         */
                        binding.apply {
                            shimmerFrameLayout.stopShimmerAnimation()
                            recyclerView.visibility = View.VISIBLE
                            shimmerFrameLayout.visibility = View.GONE
                        }
                        list = it.responseList as ArrayList<Article>
                        setAdapter()
                    }
                    else -> {
                        Toast.makeText(this@DataMainActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    /**
     * search the data in api
     */
    private fun search() {
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                loadApi(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

    }

    private fun loadApi(query: String) {
        responseViewModel.getData(query)
        responseViewModel.liveDataForSearch.observe(this) {
            it.let {
                when (it) {
                    is NetworkHelperClass.OnSuccess_2 -> {
                        tempList = it.responseList as ArrayList<Article>
                        setAdapter()
                    }

                    is NetworkHelperClass.OnFailure -> {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    /**
     * search in existing recycler view list
     */
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//
//        val item: MenuItem = menu!!.findItem(R.id.action_search)
//        val searchView = item.actionView as SearchView
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return true
//            }
//            @SuppressLint("NotifyDataSetChanged")
//            override fun onQueryTextChange(newText: String?): Boolean {
//                if(newText!!.isNotEmpty()){
//                    tempList.clear()
//                    var search = newText.toLowerCase(Locale.getDefault())
//
//                    for (article in list){
//                        if (article.title.toLowerCase(Locale.getDefault()).contains(search)){
//                            tempList.add(article)
//                        }
//                        binding.recyclerView.adapter!!.notifyDataSetChanged()
//                    }
//                }
//                else{
//                    tempList.clear()
//                    tempList.addAll(list)
//                    binding.recyclerView.adapter!!.notifyDataSetChanged()
//                }
//                return true
//            }
//        })
//        return super.onCreateOptionsMenu(menu)
//
//    }
}