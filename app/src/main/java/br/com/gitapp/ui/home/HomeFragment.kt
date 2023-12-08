package br.com.gitapp.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gitapp.R
import br.com.gitapp.databinding.FragmentHomeBinding
import br.com.gitapp.domian.model.User
import br.com.gitapp.domian.util.ConnectivityInterceptor
import br.com.gitapp.domian.util.Constants.USER_NAME
import br.com.gitapp.domian.util.ImageLoader
import br.com.gitapp.ui.home.adapter.UserAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var userAdapter: UserAdapter? = null
    private var allUser: MutableList<User> = mutableListOf()
    private var first = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        showLoading(true)

        binding.includeNoInternet.buttonNoInternet.setOnClickListener {
            viewModel.checkInternet()
        }

        viewModel.internet.observe(viewLifecycleOwner){
            if(it){
                showLoading(true)
                viewModel.getListUser()
            }
            showNoInternet(it)
        }

        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                s?.let { char ->
                    showEmpty((userAdapter?.filter(char, allUser) ?: 0) <= 0)
                }
            }
        })


        viewModel.listUser.observe(viewLifecycleOwner){ result ->
            result?.let { listUser ->
                if(first && result.isNotEmpty()) {
                    allUser.addAll(result)
                    first = false
                }
                userAdapter = UserAdapter(result?: mutableListOf(),
                    ImageLoader(requireContext())) {
                    val bundle = Bundle()
                    bundle.putString(USER_NAME, it)
                    findNavController().navigate(R.id.action_navigation_home_to_navigation_user, bundle)
                }
                binding.recyclerViewUser.adapter = userAdapter
                binding.recyclerViewUser.setHasFixedSize(true)
                binding.recyclerViewUser.layoutManager = LinearLayoutManager(context)
                binding.recyclerViewUser.itemAnimator = DefaultItemAnimator()
                binding.groupShowUser.visibility = View.VISIBLE
                showLoading(false)
            }?: run {
                showEmpty(true)
            }

        }

        return root
    }

    private fun showNoInternet(isOnline: Boolean){
        showLoading(isOnline)
        binding.includeNoInternet.constraintLayoutNoInternet.visibility = if(isOnline) View.GONE else View.VISIBLE
        if(isOnline) {
            binding.includeNoInternet.animationNoInternetMessage.pauseAnimation()
        } else {
            binding.includeNoInternet.animationNoInternetMessage.playAnimation()

        }
    }

    private fun showLoading(show: Boolean){
        binding.includeLoading.constraintLayoutLoading.visibility = if(show) View.VISIBLE else View.GONE
        if(show)
            binding.includeLoading.animationLoading.playAnimation()
        else
            binding.includeLoading.animationLoading.pauseAnimation()
    }

    private fun showEmpty(show: Boolean){
        binding.includeEmpty.constraintLayoutEmpty.visibility = if(show) View.VISIBLE else View.GONE
        binding.recyclerViewUser.visibility = if(show) View.GONE else View.VISIBLE
        if(show) {
            binding.includeEmpty.animationEmpty.playAnimation()
        } else {
            binding.includeEmpty.animationEmpty.pauseAnimation()
        }
    }
}