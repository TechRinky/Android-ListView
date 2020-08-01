package com.example.techmassignment.activities

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.techmassignment.DataListViewAdapter
import com.example.techmassignment.DataOfImagesBean
import com.example.techmassignment.DataViewModel
import com.example.techmassignment.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_layout.*
import kotlinx.android.synthetic.main.toolbar.*


/**
 * This is the main class which is responsible to show data in list
 */
class HomeActivity : AppCompatActivity() {
    private lateinit var dataListViewAdapter: DataListViewAdapter
    private lateinit var viewModel: DataViewModel
    val rowsList = ArrayList<DataOfImagesBean.Row>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViewModel()
        initView()
        observeData()
        setLoaderObserver()
        getData()
    }

    /**
     * This method is responsible to intialize views
     */
    private fun initView() {
        dataListViewAdapter =
            DataListViewAdapter(rowsList, this)
        data_listview.adapter = dataListViewAdapter
        swipeContainer.setOnRefreshListener {
            getData()
        }
       setSwipeLayout()
    }

    /**
     * This method is responsible to handle swipe refresh with listview
     */
    private fun setSwipeLayout() {
        data_listview.setOnScrollListener(object : AbsListView.OnScrollListener{
            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                if (data_listview.getChildAt(0) != null) {
                    val topRowVerticalPosition =
                        if (data_listview == null || data_listview.getChildCount() === 0) 0 else data_listview.getChildAt(
                            0
                        ).getTop()

                    swipeContainer.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);

                }
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

            }

        })
    }

    /**
     * This method is used to set observer on the loader.
     */
    private fun setLoaderObserver() {
        viewModel.loadingState.observe(this, Observer {
            showProgress(it!!)
        })
    }

    private fun observeData() {
        viewModel.liveData.observe(this, Observer {
            swipeContainer.isRefreshing = false
            if (it != null) {
                it.rows?.let { it1 ->
                    rowsList.clear()
                    rowsList.addAll(it1) }
                dataListViewAdapter.notifyDataSetChanged()
                setTitleOfScreen(it)
            }


        })
    }

    /**
     * This method set the title of the screen
     */
    private fun setTitleOfScreen(it: DataOfImagesBean) {
        title_tv.setText(it.title)
    }

    /**
     * This method is responsible to call data from viewmodel
     */
    private fun getData() {
        viewModel.getDataFromAsset()
    }

    /**
     * This method is responsible to initialize the viewmodel
     */
    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
    }

    /**
     * This method is responsible to show progress bar when background task is performed
     * @param show This is the boolean param which shows flag to show progress bar or not
     */
    fun showProgress(show: Boolean) {
        if (a_progress_bar != null) {
            a_progress_bar?.visibility = if (show) View.VISIBLE else View.GONE
        }
    }

}