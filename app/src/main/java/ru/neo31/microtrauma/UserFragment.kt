package ru.neo31.microtrauma

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.neo31.microtrauma.databinding.FragmentUserBinding
import ru.neo31.microtrauma.retrofit.MainApi
import com.squareup.picasso.Picasso
import ru.neo31.microtrauma.retrofit.AuthRequest

fun concat(vararg string: String): String {
    val sb = StringBuilder()
    for (s in string) {
        sb.append(s)
    }

    return sb.toString()
}

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var mainApi: MainApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRetrofit()
        viewModel.userData.observe(viewLifecycleOwner){UserData ->
            requireActivity().runOnUiThread {
                if(UserData != null){
                    Picasso.get().load(UserData.image).into(binding.userLogoimageView)
                    binding.nameTextView.text = concat (UserData.firstName," ", UserData.lastName)
                    //binding.lastNameTextView.text = UserData.lastName
                    //binding.genderTextView.text = UserData.gender

                }
            }
        }
        binding.apply {

            bNewTrauma.setOnClickListener {
                findNavController().navigate(R.id.action_userFragment_to_NewTraumaFragment)
            }
        }
    }


    private fun initRetrofit(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        mainApi = retrofit.create(MainApi::class.java)
    }
}