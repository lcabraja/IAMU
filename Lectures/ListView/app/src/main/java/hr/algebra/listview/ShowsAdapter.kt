package hr.algebra.listview

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ShowsAdapter(context: Context, shows: List<Show>) : ArrayAdapter<Show>(context, 0, shows) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // we're reusing the previous view object to prevent from unnecessary inflating

        val viewHolder: ViewHolder
        var view = convertView
        if (view == null) {
            view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item, parent, false)

            viewHolder = ViewHolder(
                view.findViewById(R.id.ivShow),
                view.findViewById(R.id.tvShow)
            )

            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        val show = getItem(position)

        viewHolder.hivShow.setImageResource(show!!.picture)
        viewHolder.htvShow.text = show!!.title

        return view!!
    }

    private class ViewHolder(val hivShow: ImageView, val htvShow: TextView)

}