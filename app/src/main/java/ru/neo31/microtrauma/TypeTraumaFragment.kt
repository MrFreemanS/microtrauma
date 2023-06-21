package ru.neo31.microtrauma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.neo31.microtrauma.adapters.TraumaTypeAdapter
import ru.neo31.microtrauma.databinding.FragmentTypeTraumaBinding


class TypeTraumaFragment : Fragment() {

    private lateinit var binding: FragmentTypeTraumaBinding

    private val adapter = TraumaTypeAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTypeTraumaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}