package totenhund.com.moviepicker.card_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import totenhund.com.moviepicker.R
import totenhund.com.moviepicker.data.actors.Actor
import totenhund.com.moviepicker.databinding.ActorViewBinding

class ActorsAdapter(private var actors: List<Actor>) :
    RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ActorsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.actor_view,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.apply {
            binding.actor = actors[position]
            holder.binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = actors.size


    inner class ActorsViewHolder(val binding: ActorViewBinding) :
        RecyclerView.ViewHolder(binding.root)

}