package info.hellovass.meizhi.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import info.hellovass.meizhi.R
import info.hellovass.meizhi.data.MeiZhi
import info.hellovass.meizhi.net.Status
import info.hellovass.meizhi.ext.bindPuller
import info.hellovass.meizhi.ext.obtainViewModel
import info.hellovass.meizhi.lib.LinearLayoutManagerDelegate
import info.hellovass.meizhi.net.Resource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mMainVM: MainVM by lazy {

        this.obtainViewModel(MainVM::class.java)
    }

    private val mMeiZhisAdapter: MeiZhisAdapter by lazy {

        MeiZhisAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRcv()

        observeData()
    }

    private fun setupRcv() {

        rcvList.apply {

            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mMeiZhisAdapter

            this.bindPuller(LinearLayoutManagerDelegate(LinearLayoutManager(context)), loadMoreHandler = {

                mMainVM.getMeiZhis()
            })
        }
    }

    private fun observeData() {

        mMainVM.meizhis.observe(this, Observer<Resource<List<MeiZhi>>> { response -> handleResponse(response) })
    }

    private fun handleResponse(reponse: Resource<List<MeiZhi>>?) {

        when (reponse?.status) {

            Status.SUCCESS -> {

                mMeiZhisAdapter.setDatas(reponse.data)
            }

            Status.ERROR -> {

            }

            Status.LOADING -> {

            }
        }
    }
}




