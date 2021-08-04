package totenhund.com.moviepicker.picker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.material.transition.MaterialElevationScale
import com.yuyakaido.android.cardstackview.*
import com.yuyakaido.android.cardstackview.Direction.*
import totenhund.com.moviepicker.R
import totenhund.com.moviepicker.databinding.FragmentMoviePickerBinding
import totenhund.com.moviepicker.data.movies.Movie
import totenhund.com.moviepicker.picker.adapter.MovieAdapterListener
import totenhund.com.moviepicker.picker.adapter.MoviesAdapter


class MoviePickerFragment : Fragment(), CardStackListener, MovieAdapterListener {

    private lateinit var binding: FragmentMoviePickerBinding
    private lateinit var viewModel: MoviePickerViewModel
    private val adapter = MoviesAdapter(this)
    private lateinit var layoutManager: CardStackLayoutManager
    private val manager by lazy { CardStackLayoutManager(requireContext(), this) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_picker,
            container,
            false
        )

        setupSwipeCards()
        setupSwipeNavigationButtons()

        viewModel = ViewModelProvider(this)
            .get(MoviePickerViewModel::class.java)


        viewModel.movies.observe(viewLifecycleOwner, {
            adapter.setMovies(it)
        })

        return binding.root
    }

    override fun onMovieClicked(cardView: View, movie: Movie) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.collapse_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.collapse_motion_duration_large).toLong()
        }
        val movieCardDetailTransitionName = getString(R.string.movie_card_transition_name)
        val extras = FragmentNavigatorExtras(cardView to movieCardDetailTransitionName)
        val directions = MoviePickerFragmentDirections.actionMoviePickerFragmentToCardDetailsFragment(movie.id)
        findNavController().navigate(directions, extras)
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

        when(direction){
            Left -> viewModel.onSwipeLeft()
            Right -> viewModel.onSwipeRight()
        }
    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

    private fun setupSwipeCards(){

        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)

        layoutManager = CardStackLayoutManager(requireContext(), this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }

        binding.stackView.layoutManager = layoutManager
        binding.stackView.adapter = adapter
        binding.stackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    private fun setupSwipeNavigationButtons(){

        binding.skipButton.setOnClickListener {
            val settingLeft = SwipeAnimationSetting.Builder()
                .setDirection(Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(settingLeft)
            binding.stackView.swipe()
        }

        binding.rewindButton.setOnClickListener {
            val settingRewind = RewindAnimationSetting.Builder()
                .setDirection(Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setRewindAnimationSetting(settingRewind)
            binding.stackView.rewind()
            viewModel.onRewind()
        }

        binding.likeButton.setOnClickListener {
            val settingRight = SwipeAnimationSetting.Builder()
                .setDirection(Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(settingRight)
            binding.stackView.swipe()
        }
    }


}