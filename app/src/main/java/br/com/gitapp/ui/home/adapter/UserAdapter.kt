package br.com.gitapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import br.com.gitapp.databinding.RowUserBinding
import br.com.gitapp.domian.model.User
import br.com.gitapp.domian.util.ImageLoader
import java.util.Locale

class UserAdapter(
    private var listUser: MutableList<User>,
    private var imageLoader: ImageLoader,
    private var listener: (login: String) -> Unit)
    : RecyclerView.Adapter<UserItemViewHolder>() {

    fun filter(constraint: CharSequence?, allUser: MutableList<User>): Int {

        val filteredList: MutableList<User> = mutableListOf()

        if (constraint.isNullOrBlank()) {
            filteredList.addAll(allUser)
        } else {
            val filterPattern: String = constraint.toString().lowercase(Locale.getDefault()).trim()
            allUser.forEach {
                if (it.login?.lowercase(Locale.getDefault())?.contains(filterPattern) == true && it.id != "-1") {
                    filteredList.add(it)
                }
            }

            filteredList.toMutableList().sortedBy { item -> item.login }
        }

        listUser.clear()
        listUser.addAll(filteredList)
        notifyDataSetChanged()

        return filteredList.count()
    }

    override fun getItemCount(): Int {
        return listUser.count()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder(RowUserBinding.inflate(LayoutInflater.from(parent.context),
                parent,false)
        )
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.setData(listUser[holder.adapterPosition], imageLoader, listener)
    }
}