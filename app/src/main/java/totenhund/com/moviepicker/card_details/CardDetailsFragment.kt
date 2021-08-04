package totenhund.com.moviepicker.card_details

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import totenhund.com.moviepicker.R
import totenhund.com.moviepicker.card_details.adapter.ActorsAdapter
import totenhund.com.moviepicker.data.actors.Actor
import totenhund.com.moviepicker.databinding.FragmentCardDetailsBinding
import totenhund.com.moviepicker.data.movies.MovieStore


class CardDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCardDetailsBinding
    private val args: CardDetailsFragmentArgs by navArgs()
    private val movieId: Long by lazy { args.movieId }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.collapse_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().getColor(R.color.white))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_card_details,
            container,
            false
        )
        val movieAttached = MovieStore().get(movieId)
        binding.actorsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.actorsList.adapter = ActorsAdapter(movieAttached?.actors ?: ArrayList<Actor>())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            val movieAttached = MovieStore().get(movieId)
            this.movie = movieAttached
        }
    }
}