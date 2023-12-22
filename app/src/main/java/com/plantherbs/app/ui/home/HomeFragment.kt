
package com.plantherbs.app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.plantherbs.app.R
import com.plantherbs.app.ViewModelFactory
import com.plantherbs.app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val layoutManager = GridLayoutManager(requireContext(), 2)
            rvHerbs.layoutManager = layoutManager
        }

        setWelcomeText()
        setHerbsData()
    }

    private fun setHerbsData() {
        viewModel.getAllHerbs().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    setLoading(true)
                }
                is Result.Success -> {
                    val adapter = HerbsListAdapter()
                    adapter.submitList(result.data.data)
                    binding.rvHerbs.adapter = adapter
                    setLoading(false)
                }
                is Result.Error -> {
                    setLoading(false)
                    Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setWelcomeText() {
        viewModel.getSession().observe(viewLifecycleOwner) { session ->
            if (session.userId.isNotEmpty()) {
                viewModel.getUserById(session.userId).observe(viewLifecycleOwner) { result ->
                    when (result) {
                        is Result.Loading -> {
                            binding.welcome.text = getString(R.string.welcome, "Loading...")
                        }
                        is Result.Success -> {
                            val firstname = result.data.data?.fullname?.split(" ")?.firstOrNull()
                            binding.welcome.text = getString(R.string.welcome, "${firstname}")
                        }
                        is Result.Error -> {
                            Toast.makeText(
                                requireContext(),
                                result.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            } else {
                Log.d("SESSION", "Session not Found")
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
