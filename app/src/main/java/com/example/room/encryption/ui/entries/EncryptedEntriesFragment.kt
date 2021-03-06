package com.example.room.encryption.ui.entries

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.room.R
import kotlinx.android.synthetic.main.fragment_entries.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EncryptedEntriesFragment : Fragment() {

    private val viewModel: EncryptedEntriesViewModel by viewModel()
    private val adapter: EncryptedEntriesAdapter = EncryptedEntriesAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycle.addObserver(viewModel)
    }

    override fun onDetach() {
        super.onDetach()
        lifecycle.removeObserver(viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_encrypted_entries, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_export_db -> {
                viewModel.onExportDatabaseClicked()
                true
            }
            R.id.menu_export_decrypted_db -> {
                viewModel.onExportDecryptedDatabaseClicked()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
                is EncryptedEntriesEvents.Loading -> progress_bar.visibility = View.VISIBLE
                is EncryptedEntriesEvents.EntriesLoaded -> {
                    progress_bar.visibility = View.GONE
                    adapter.setItems(it.entries)
                }
                is EncryptedEntriesEvents.AddEntry -> {
                    progress_bar.visibility = View.GONE
                    adapter.addItem(it.entry)
                }
                is EncryptedEntriesEvents.RemoveEntry -> {
                    progress_bar.visibility = View.GONE
                    adapter.removeItem(it.entry)
                }
                is EncryptedEntriesEvents.ExportDatabaseFinished -> progress_bar.visibility = View.GONE
            }
        })
    }
}