package ru.pervukhin.gitwatcher.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.pervukhin.gitwatcher.R
import ru.pervukhin.gitwatcher.domain.Repository
import java.text.SimpleDateFormat
import java.util.*

class RepositoryAdapter(private val listener: ClickWatchListener): RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    private var list: List<Repository> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false))
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val name = holder.itemView.findViewById<TextView>(R.id.name)
        val description  = holder.itemView.findViewById<TextView>(R.id.description)
        val iconProfile = holder.itemView.findViewById<ImageView>(R.id.icon_profile)
        val nameProfile = holder.itemView.findViewById<TextView>(R.id.name_profile)
        val dateCreated = holder.itemView.findViewById<TextView>(R.id.date_created)
        val dateLastPushed = holder.itemView.findViewById<TextView>(R.id.date_last_push)
        val watch = holder.itemView.findViewById<Button>(R.id.watch)

        val repository = list[position]
        name.text = repository.name
        repository.description.let {
            if (it == null){
                description.visibility = View.GONE
            }else{
                description.text = repository.description
            }
        }
        Glide.with(holder.itemView).load(repository.owner.profileImage).into(iconProfile)
        nameProfile.text = repository.owner.name
        val format = SimpleDateFormat("d MMMM yyyy", Locale("ru","RU"))
        dateCreated.text = format.format(repository.dateCreated)
        dateLastPushed.text = format.format(repository.dateLastPushed)

        watch.setOnClickListener {
            listener.onCLickWatch(repository)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<Repository>){
        this.list = list
        notifyDataSetChanged()
    }

    class RepositoryViewHolder(view: View): RecyclerView.ViewHolder(view)
}