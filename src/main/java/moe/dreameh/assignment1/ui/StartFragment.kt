package moe.dreameh.assignment1.ui

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.start_fragment.*

import moe.dreameh.assignment1.R

class startFragment : Fragment() {

    private var mAdapter: RecyclerView.Adapter<*>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView  = inflater.inflate(R.layout.start_fragment, container, false)
        // Setting up the starting view's spinner for categories.
        setupSpinner(rootView.context)

        return rootView
    }

    private fun setupRecyclerView() {
        // RecyclerView settings
        recycler_view.setHasFixedSize(true)

        // using a linear layout manager
        recycler_view.layoutManager = LinearLayoutManager(activity)

        // Specify adapter for recyclerView
        //mAdapter = AdviceAdapter(adviceList)
        recycler_view.adapter = mAdapter
    }

    private fun setupSpinner(context : Context) {
        ArrayAdapter.createFromResource(
                context,
                R.array.categories,
                android.R.layout.simple_spinner_item).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ViewModelProviders.of(this).get(SharedViewModel::class.java)
    }



}
