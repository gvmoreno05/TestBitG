package com.vmoreno.bapp.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vmoreno.bapp.R;
import com.vmoreno.bapp.model.Address;
import com.vmoreno.bapp.model.User;
import com.vmoreno.bapp.view.MapsActivity;

import java.util.List;

/**
 * Created by menis on 3/07/2017.
 */

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UserViewHolder> {

    private List<User> listUser;
    public Context ctx;


    public AdapterUser(List<User> listUser, Context context) {
        this.listUser = listUser;
        this.ctx = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder,final int position) {
        holder.txtName.setText(listUser.get(position).getName());
        holder.txtUserName.setText(listUser.get(position).getUsername());
        holder.txtEmail.setText(listUser.get(position).getEmail());
        holder.txtCompany.setText(listUser.get(position).getCompany().getName());
        final Address address = listUser.get(position).getAddress();
        holder.txtAddress.setText(address.getStreet() + " " + address.getSuite() + " " + address.getCity());
        holder.txtPhone.setText(listUser.get(position).getPhone());
        holder.cViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, MapsActivity.class);
                i.putExtra("name", listUser.get(position).getName());
                i.putExtra("city",address.getCity());
                i.putExtra("street", address.getStreet());
                i.putExtra("suite", address.getSuite());
                i.putExtra("latitude", address.getGeo().getLat());
                i.putExtra("longitude", address.getGeo().getLng());
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgUser;
        public TextView txtName;
        public TextView txtUserName;
        public TextView txtEmail;
        public TextView txtAddress;
        public TextView txtPhone;
        public TextView txtCompany;
        public CardView cViewUsers;

        public UserViewHolder(View itemView) {
            super(itemView);
            cViewUsers = (CardView) itemView.findViewById(R.id.cv_users);
            imgUser = (ImageView) itemView.findViewById(R.id.img_users);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtUserName = (TextView) itemView.findViewById(R.id.txt_username);
            txtEmail = (TextView) itemView.findViewById(R.id.txt_email);
            txtAddress = (TextView) itemView.findViewById(R.id.txt_address);
            txtPhone = (TextView) itemView.findViewById(R.id.txt_phone);
            txtCompany = (TextView) itemView.findViewById(R.id.txt_company);
        }
    }

}
