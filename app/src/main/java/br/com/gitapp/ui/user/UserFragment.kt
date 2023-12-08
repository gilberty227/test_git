package br.com.gitapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gitapp.R
import br.com.gitapp.databinding.FragmentHomeBinding
import br.com.gitapp.databinding.FragmentUserInfoBinding
import br.com.gitapp.domian.model.Repository
import br.com.gitapp.domian.model.User
import br.com.gitapp.domian.util.Constants.USER_NAME
import br.com.gitapp.domian.util.ImageLoader
import br.com.gitapp.ui.user.adapter.RepositoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
    private val viewModel: UserViewModel by viewModels()

    private lateinit var binding: FragmentUserInfoBinding
    private var userName = ""

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        userName = arguments?.getString(USER_NAME, "").orEmpty()
        showLoading(true)
        setObservers(userName)

        return root
    }

    private fun setObservers(userName: String) {
        viewModel.getInfo(userName)
        viewModel.userInfo.observe(viewLifecycleOwner){ result ->
            result?.let {
                updateUser(it, ImageLoader(requireContext()))
            }

        }

        viewModel.listRepository.observe(viewLifecycleOwner){ result ->
            result?.let {
                if(it.isEmpty()){
                    showEmpty(true)
                }

                setRepositoryAdapter(it)
                showLoading(false)
            }

        }
    }

    private fun updateUser(user: User?, imageLoader: ImageLoader){
        user?.let { userNotNull ->
            imageLoader.load(user.avatarUrl.orEmpty(), binding.imageViewUserInfo)
            binding.textViewUserName.text = userNotNull.name
            binding.textViewUserCompany.text = userNotNull.company
            binding.textViewUserLocation.text = userNotNull.location
            binding.textViewUserFollowers.text = getString(R.string.user_followers_following,
                userNotNull.followers.toString(), userNotNull.following.toString())
        }
    }

    private fun setRepositoryAdapter(listRepository: MutableList<Repository>){
        binding.recyclerViewRepositories.adapter = RepositoryAdapter(listRepository)
        binding.recyclerViewRepositories.setHasFixedSize(true)
        binding.recyclerViewRepositories.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewRepositories.itemAnimator = DefaultItemAnimator()
    }

    private fun showLoading(show: Boolean){
        binding.includeLoading.constraintLayoutLoading.visibility = if(show) View.VISIBLE else View.GONE
        binding.groupShowUserInfo.visibility = if(show) View.GONE else View.VISIBLE
        if(show) {
            binding.includeLoading.animationLoading.playAnimation()
        } else {
            binding.includeLoading.animationLoading.pauseAnimation()
        }
    }

    private fun showEmpty(show: Boolean){
        binding.includeEmpty.constraintLayoutEmpty.visibility = if(show) View.VISIBLE else View.GONE
        binding.recyclerViewRepositories.visibility = if(show) View.GONE else View.VISIBLE
        if(show) {
            binding.includeEmpty.animationEmpty.playAnimation()
        } else {
            binding.includeEmpty.animationEmpty.pauseAnimation()
        }
    }


}