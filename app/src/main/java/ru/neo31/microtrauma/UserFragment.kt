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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
            if(UserData != null) {
                CoroutineScope(Dispatchers.IO).launch{
                    val response = mainApi.getUserbyID()
                    val user = response.body()
                    requireActivity().runOnUiThread {
                        if (user !=null)
                        {
                            Picasso.get().load(UserData.image).into(binding.userLogoimageView)
                            binding.nameTextView.text = concat (user.firstName," ", user.lastName)
                            binding.genderTextView.text = user.gender
                            binding.birthDateTextView.text = user.birthDate
                            binding.positionTextView.text = user.company.title
                            binding.departmentTextView.text = user.company.department

                        }
                    }

                }
            }



        }
        binding.apply {

            bNewTrauma.setOnClickListener {
                findNavController().navigate(R.id.action_userFragment_to_typeTraumaFragment)
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