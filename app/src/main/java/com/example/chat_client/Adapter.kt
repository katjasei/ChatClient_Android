/*Ekaterina Seikkinen 1806881
Adapter class - recycler view adapter which binds data to views
*/

package com.example.chat_client
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Adapter(private val values: MutableList<NewMessage>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun getItemCount() = values.size                                                                //displays the total number of items

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {                                    //creates new object ViewHolder, when it is necessary
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.message_item, p0, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {                                                  // fills the object ViewHolder with new data
        p0.textView?.text = values[p1].message
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {                                    //class for storing data
        var textView: TextView? = null

        init {
            textView = itemView.findViewById(R.id.text_message_item)
        }
    }
    fun add (input: String){                                                                                     //adds new message-element to the Adapter and notify that data changed
        values.add(NewMessage(input))
        notifyDataSetChanged()
    }
}