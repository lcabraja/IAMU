package hr.algebra.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ShowsAdapter(context: Context, shows: List<Show>) : ArrayAdapter<Show>(context, 0, shows) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val show = getItem(position)

        val ivShow = view.findViewById<ImageView>(R.id.ivShow)
        val tvShow = view.findViewById<TextView>(R.id.tvShow)

        ivShow.setImageResource(show!!.picture)
        tvShow.text = show!!.title

        return view
    }
}