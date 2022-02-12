package com.example.weatherapplication.ui.favourit

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.ItemFavouriteBinding
import android.widget.Toast
import com.example.weatherapplication.R


class FavouriteAdapter (val favouriteList: MutableList<String>, val context: Context,  val inter:   InterfaceFvourite) :
    RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                com.example.weatherapplication.R.layout.item_favourite,
                parent,
                false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val loc:String= favouriteList.get(position)
        Log.i("ttttttttttttttttttttttt", "onBindViewHolder: "+favouriteList.size)
               holder.itemBinding.tvFavLocation.setText(loc)
        holder.itemView.setOnClickListener{

                    Toast.makeText(context, "hello ", Toast.LENGTH_LONG).show();
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.

            inter.openFragment()
        }


        holder.itemBinding.mymenue.setOnClickListener{

            AlertDialog.Builder(it.getContext()).setMessage("are you sure to delete this location ?")
                .setNegativeButton("cancle", null)
                .setPositiveButton("delete") { dialog, which ->
//                    ContactsDataBase.getInstance(v.getContext()).contactDao()
//                        .deleteContact(myContact)
//                    contacts.removeAt(position)
//                    notifyDataSetChanged()
                    favouriteList.removeAt(position)
                    notifyDataSetChanged()
                }.show()


        }




    }

    override fun getItemCount(): Int {
        return favouriteList.size
    }


    class ViewHolder(itemBinding: ItemFavouriteBinding) :
        RecyclerView.ViewHolder(itemBinding.getRoot()) {
        var itemBinding: ItemFavouriteBinding

        init {
            this.itemBinding = itemBinding
        }
    }


}