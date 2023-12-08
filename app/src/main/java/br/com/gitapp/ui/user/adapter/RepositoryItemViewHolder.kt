package br.com.gitapp.ui.user.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.gitapp.databinding.RowRepositoriesBinding
import br.com.gitapp.databinding.RowUserBinding
import br.com.gitapp.domian.model.Repository
import br.com.gitapp.domian.model.User
import br.com.gitapp.domian.util.ImageLoader
import br.com.gitapp.domian.util.parseISO8601DateToString

class RepositoryItemViewHolder(private val binding: RowRepositoriesBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setData(repository: Repository){
        binding.textViewRepositoryLanguage.text = repository.language
        binding.textViewRepositoryCountWatchers.text = repository.watchers.toString()
        binding.textViewRepositoryCountForks.text = repository.forks.toString()
        binding.textViewRepositoryName.text = repository.fullName
        binding.textViewRepositoryUpdate.text = repository.update?.parseISO8601DateToString()
        binding.textViewRepositoryPublic.text = repository.visibility
    }
}