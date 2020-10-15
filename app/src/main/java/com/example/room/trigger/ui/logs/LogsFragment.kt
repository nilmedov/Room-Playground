package com.example.room.trigger.ui.logs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.room.R
import kotlinx.android.synthetic.main.fragment_logs.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogsFragment : Fragment() {

    private val viewModel: LogsViewModel by viewModel()
    private val adapter: LogsAdapter = LogsAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycle.addObserver(viewModel)
    }

    override fun onDetach() {
        super.onDetach()
        lifecycle.removeObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_logs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        rv_logs.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getEvents().observe(viewLifecycleOwner) {
            when (it) {
                is LogsEvents.Loading -> progress_bar.visibility = View.VISIBLE
                is LogsEvents.LogsLoaded -> {
                    progress_bar.visibility = View.GONE
                    adapter.setItems(it.logs)
                }
            }
        }
    }
}