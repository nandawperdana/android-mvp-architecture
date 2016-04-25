package com.nandawperdana.poetryandroidmvp.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nandawperdana.poetryandroidmvp.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nandawperdana on 4/25/2016.
 */
public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.ViewHolder> {
    private List<String> listAuthor;
    private Context mContext;

    public AuthorAdapter(List<String> data, Context context) {
        this.listAuthor = data;
        this.mContext = context;
    }

    public AuthorAdapter(List<String> data) {
        this.listAuthor = data;
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
        holder.bindView(listAuthor.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return listAuthor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview_row_main)
        CircleImageView imageView;
        @Bind(R.id.textview_row_main)
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            initLayout(itemView);
        }

        private void initLayout(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindView(String name) {
            if (name != null)
                textView.setText(name);

//            Picasso.with(context)
//                    .load(logoUrl)
//                    .error(R.drawable.ic_blank_avatar)
//                    .placeholder(R.drawable.ic_blank_avatar)
//                    .into(imageView);
        }
    }
}
