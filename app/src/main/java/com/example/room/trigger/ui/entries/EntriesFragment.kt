package com.example.room.trigger.ui.entries

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.room.R
import kotlinx.android.synthetic.main.fragment_entries.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EntriesFragment : Fragment() {

    private val viewModel: EntriesViewModel by viewModel()
    private val adapter: EntriesAdapter = EntriesAdapter()

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
        return inflater.inflate(R.layout.fragment_entries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
        setupObservers()
    }

    private fun setupRecyclerView() {
        rv_entries.adapter = adapter
    }

    private fun setupListeners() {
        btn_add_entry.setOnClickListener {
            viewModel.onAddEntryClicked()
        }

        btn_remove_entry.setOnClickListener {
            viewModel.onRemoveEntryClicked()
        }
    }

    private fun setupObservers() {
        viewModel.getEvents().observe(viewLifecycleOwner, Observer {
            when (it) {
                is EntriesEvents.EntriesLoaded -> adapter.setItems(it.entries)
                is EntriesEvents.AddEntry -> adapter.addItem(it.entry)
                is EntriesEvents.RemoveEntry -> adapter.removeItem(it.entry)
            }
        })
    }
}