package com.codeapin.latihanfirestore;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

public class ContactAdapter extends FireStoreAdapter<ContactAdapter.ContactViewHolder> {

    public ContactAdapter(Query query) {
        super(query);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.bind(getSnapshot(position));
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView textNama;
        private final TextView textTelepon;
        private final ImageView imageTelepon;

        public ContactViewHolder(View itemView) {
            super(itemView);
            textNama = itemView.findViewById(R.id.text_nama);
            textTelepon = itemView.findViewById(R.id.text_telepon);
            imageTelepon = itemView.findViewById(R.id.image_profile);
        }

        public void bind(DocumentSnapshot snapshot) {
            User user = snapshot.toObject(User.class);
            textNama.setText(user.getFullName());
            textTelepon.setText(user.getPhoneNumber());
            Glide.with(itemView)
                    .load(user.getPhotoUrl())
                    .into(imageTelepon);
        }
    }
}
