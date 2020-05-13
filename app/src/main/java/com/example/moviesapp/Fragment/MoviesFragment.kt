package com.example.moviesapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.UI.Adapter.MoviesAdapter
import com.example.moviesapp.UI.ViewModel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {
    private lateinit var moviesListAdapter: MoviesAdapter
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerMovies.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        moviesListAdapter = MoviesAdapter()
        recyclerMovies.adapter = moviesListAdapter
        observeViewModel()
    }

    private fun observeViewModel() {
        moviesViewModel = ViewModelProvider(this)
            .get(MoviesViewModel::class.java)
        moviesViewModel.loadMovies()
        moviesViewModel.topRatedMovies.observe(
            viewLifecycleOwner, Observer {
                recyclerMovies.visibility = View.VISIBLE
                moviesListAdapter.updateResultlist(it)
            }
        )
    }

}
