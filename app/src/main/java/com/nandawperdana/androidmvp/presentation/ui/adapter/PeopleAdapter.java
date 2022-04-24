package com.nandawperdana.androidmvp.presentation.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nandawperdana.androidmvp.R;
import com.nandawperdana.androidmvp.api.people.People;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nandawperdana on 4/25/2016.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    private List<People> listPeople;
    private Context mContext;

    public PeopleAdapter(List<People> listPeople, Context mContext) {
        this.listPeople = listPeople;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_main, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(mContext, listPeople.get(position));
    }

    @Override
    public int getItemCount() {
        return listPeople.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_row_main)
        CircleImageView imageView;
        @BindView(R.id.textview_row_main)
        TextView textView;
        @BindView(R.id.textview_row_main_subtitle)
        TextView textViewSubtitle;

        ViewHolder(View itemView) {
            super(itemView);
            initLayout(itemView);
        }

        private void initLayout(View view) {
            ButterKnife.bind(this, view);
        }

        void bindView(Context context, People data) {
            if (data.getName() != null)
                textView.setText(data.getName());
            if (data.getEmail() != null)
                textViewSubtitle.setText(data.getEmail());

            String url = data.getPic();
            Picasso.get()
                    .load(url)
                    .error(R.drawable.ic_blank_avatar)
                    .placeholder(R.drawable.ic_blank_avatar)
                    .into(imageView);
        }
    }
}
