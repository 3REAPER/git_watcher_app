package ru.pervukhin.gitwatcher.presentation.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import ru.pervukhin.gitwatcher.R
import ru.pervukhin.gitwatcher.domain.Repository

class SearchFragment : Fragment(), ClickWatchListener {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val name = view.findViewById<EditText>(R.id.name)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = RepositoryAdapter(this)
        recyclerView.adapter = adapter

        name.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.getRepositoryByName(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        viewModel.liveData.observe(viewLifecycleOwner){
            when(it){
                SearchViewModel.RepositoryState.Loading ->{}
                is SearchViewModel.RepositoryState.Success ->{
                    adapter.setList(it.list)
                }
                SearchViewModel.RepositoryState.Empty ->{}
                SearchViewModel.RepositoryState.NoInternet ->{}

            }
        }

        return view
    }

    override fun onCLickWatch(repository: Repository) {
        Toast.makeText(context, "watch", Toast.LENGTH_LONG).show()
    }
}