package com.app.yogesh_verma_movie.movie_module.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.yogesh_verma_movie.R
import com.app.yogesh_verma_movie.databinding.ItemProductionCompanyBinding
import com.app.yogesh_verma_movie.model.ProductionCompany
import com.app.yogesh_verma_movie.movie_module.viewholders.ProductionCompanyViewHolder

class ProductionCompanyAdapter:RecyclerView.Adapter<ProductionCompanyViewHolder>() {
    val productionCompanyList:MutableList<ProductionCompany> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductionCompanyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemProductionCompanyBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.item_production_company, parent, false)
        return ProductionCompanyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductionCompanyViewHolder, position: Int) {
        holder.binding.model = productionCompanyList.get(position)
    }

    override fun getItemCount(): Int {
        return productionCompanyList.size
    }

    fun getItems():MutableList<ProductionCompany>{
        return productionCompanyList as MutableList<ProductionCompany>
    }

    fun addItems(itemList: MutableList<ProductionCompany>){
        addItems(itemList,true)
    }

//    fun setOnClickListener(onClickListener:Any?){
//        this.onClickListener = onClickListener
//    }


    fun addItems(itemList: MutableList<ProductionCompany>, notifyDataSetChanged:Boolean){
        val start = productionCompanyList.size
        productionCompanyList.addAll(itemList)
        if(notifyDataSetChanged)
            notifyItemRangeInserted(start,itemList.size)

    }
}