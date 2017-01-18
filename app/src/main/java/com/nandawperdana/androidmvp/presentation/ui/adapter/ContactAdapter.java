package com.nandawperdana.androidmvp.presentation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nandawperdana.androidmvp.R;
import com.nandawperdana.androidmvp.api.contact.ContactsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nandawperdana on 4/25/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private List<ContactsModel.Contact> listContact;
    private Context mContext;

    public ContactAdapter(List<ContactsModel.Contact> listContact, Context mContext) {
        this.listContact = listContact;
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
        holder.bindView(mContext, listContact.get(position));
    }

    @Override
    public int getItemCount() {
        return listContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview_row_main)
        CircleImageView imageView;
        @Bind(R.id.textview_row_main)
        TextView textView;
        @Bind(R.id.textview_row_main_subtitle)
        TextView textViewSubtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            initLayout(itemView);
        }

        private void initLayout(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindView(Context context, ContactsModel.Contact data) {
            if (data.getName() != null)
                textView.setText(data.getName());
            if (data.getEmail() != null)
                textViewSubtitle.setText(data.getEmail());

            String url = "http://graph.facebook.com/1399656618/picture?type=square";
            Picasso.with(context)
                    .load(url)
                    .error(R.drawable.ic_blank_avatar)
                    .placeholder(R.drawable.ic_blank_avatar)
                    .into(imageView);
        }
    }
}
