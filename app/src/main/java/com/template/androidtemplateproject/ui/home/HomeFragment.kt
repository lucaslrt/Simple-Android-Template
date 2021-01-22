package com.template.androidtemplateproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.template.androidtemplateproject.R
import com.template.androidtemplateproject.data.ApiResource
import com.template.androidtemplateproject.util.LogUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var tvDataContent : TextView

    /*// USAR QUANDO NÃO ESTIVER USANDO INJEÇÃO DE DEPENDENCIA
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, HomeViewModel.HomeViewModelFactory(HomeRepository())).get(
            HomeViewModel::class.java
        )
    }*/

    // VIEWMODEL USANDO KOIN
    private val homeViewModel : HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        tvDataContent = root.findViewById(R.id.tv_content_data)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObservers()
        homeViewModel.getContent()
    }

    private fun initObservers() {
        homeViewModel.contentList.observe(viewLifecycleOwner, Observer {
            if(it.status == ApiResource.Companion.ApiStatus.LOADING){
                LogUtil.print("Loading....")
            } else {
                LogUtil.print("Job done.")
                if (!it.data.isNullOrEmpty()) {
                    tvDataContent.text = it.data[0].data
                }
            }
        })
    }
}