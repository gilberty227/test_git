package br.com.gitapp.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.gitapp.databinding.RowUserBinding
import br.com.gitapp.domian.model.User
import br.com.gitapp.domian.util.ImageLoader

class UserItemViewHolder(private val binding: RowUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setData(user: User, imageLoader: ImageLoader, listener: (login: String) -> Unit) {

        imageLoader.loadCircled(user.avatarUrl.orEmpty(), binding.imageViewUser)
        binding.textViewUserLogin.text = user.login
        binding.textViewUserType.text = user.type

        binding.cardViewItemUser.setOnClickListener {
            listener(user.login.orEmpty())
        }
    }
}