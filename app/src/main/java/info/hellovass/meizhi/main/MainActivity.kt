package info.hellovass.meizhi.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import info.hellovass.meizhi.R
import info.hellovass.meizhi.dto.MeiZhiDTO
import info.hellovass.meizhi.ext.createVM
import info.hellovass.meizhi.lib.network.Resource
import info.hellovass.meizhi.lib.network.Status
import info.hellovass.planner.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var mainVM: MainVM

    private lateinit var meiZhisAdapter: MeiZhisAdapter

    override fun getLayoutResId(): Int {

        return R.layout.activity_main
    }

    override fun obtainVM() {

        mainVM = createVM(MainVM::class.java)
    }

    override fun initData(savedInstanceState: Bundle?) {

        refreshLayout.apply {

            setOnRefreshListener(mainVM.provideOnRefreshListener())
        }


        rcvList.apply {

            layoutManager = LinearLayoutManager(this@MainActivity)

            addOnScrollListener(mainVM.provideOnScrollListener())

            meiZhisAdapter = MeiZhisAdapter(mainVM.meiZhisDataSet)
            adapter = meiZhisAdapter
        }

        mainVM.loadData(1)
    }

    override fun observeData() {

        mainVM.meiZhisObservable.observe(this, Observer<Resource<List<MeiZhiDTO>>> {

            it?.let {

                handleResult(it)
            }
        })
    }

    private fun handleResult(resource: Resource<List<MeiZhiDTO>>) {

        when (resource.status) {

            Status.SUCCESS -> {

                resource.data?.let {

                    val result = DiffUtil.calculateDiff(DiffCallBack(meiZhisAdapter.datas, it))
                    result.dispatchUpdatesTo(meiZhisAdapter)
                }
            }
            Status.ERROR -> {

            }
            Status.LOADING -> {

            }
        }
    }
}




