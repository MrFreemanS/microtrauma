package ru.neo31.microtrauma.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.neo31.microtrauma.R
import ru.neo31.microtrauma.databinding.TraumaTypeItemBinding
import ru.neo31.microtrauma.dataclasses.TraumaDataClass


class TraumaTypeAdapter:RecyclerView.Adapter<TraumaTypeAdapter.TraumaTypeHolder> (){

    private val TraumaList:  List<TraumaDataClass> = listOf(
        TraumaDataClass(R.drawable.ic_hand, "Ссадины"),
        TraumaDataClass(R.drawable.ic_cubit, "Ушибы"),
        TraumaDataClass(R.drawable.ic_little_cut, "Незначительные порезы"),
        TraumaDataClass(R.drawable.ic_ukol, "Уколы "),
        TraumaDataClass(R.drawable.ic_palec, "Царапины "),
        TraumaDataClass(R.drawable.ic_ojog, "Ожоги первой степени")
    )


    class TraumaTypeHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding = TraumaTypeItemBinding.bind(item)
        fun bind(trauma:TraumaDataClass) = with(binding){
            TraumaTypeItemImageView.setImageResource(trauma.imageId)
            TraumaTypeItemTextView.text= trauma.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraumaTypeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trauma_type_item, parent, false)
        return TraumaTypeHolder(view)
    }

    override fun getItemCount(): Int {
        return TraumaList.size
    }

    override fun onBindViewHolder(holder: TraumaTypeHolder, position: Int) {
        holder.bind(TraumaList[position])
    }
}