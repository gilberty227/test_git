package br.com.gitapp.ui.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gitapp.databinding.RowRepositoriesBinding
import br.com.gitapp.databinding.RowUserBinding
import br.com.gitapp.domian.model.Repository
import br.com.gitapp.domian.model.User
import br.com.gitapp.domian.util.ImageLoader

class RepositoryAdapter(
    private var listRepository: MutableList<Repository>)
    : RecyclerView.Adapter<RepositoryItemViewHolder>() {

    override fun getItemCount(): Int {
        return listRepository.count()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryItemViewHolder {
        return RepositoryItemViewHolder(RowRepositoriesBinding.inflate(LayoutInflater.from(parent.context),
                parent,false)
        )
    }

    override fun onBindViewHolder(holder: RepositoryItemViewHolder, position: Int) {
        holder.setData(listRepository[holder.adapterPosition])
    }
}