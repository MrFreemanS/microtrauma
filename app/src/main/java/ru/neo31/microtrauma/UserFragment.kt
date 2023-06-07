package ru.neo31.microtrauma

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.neo31.microtrauma.databinding.FragmentUserBinding
import ru.neo31.microtrauma.retrofit.MainApi
import com.squareup.picasso.Picasso


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

        viewModel.userData.observe(viewLifecycleOwner){UserData ->
            requireActivity().runOnUiThread {
                if(UserData != null){
                    Picasso.get().load(UserData.image).into(binding.imageView)
                    binding.nameTextView.text = UserData.firstName
                    binding.lastNameTextView.text = UserData.lastName
                    binding.genderTextView.text = UserData.gender
                }
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