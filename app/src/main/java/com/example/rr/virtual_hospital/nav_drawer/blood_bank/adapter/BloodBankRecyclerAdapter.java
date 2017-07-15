package com.example.rr.virtual_hospital.nav_drawer.blood_bank.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.nav_drawer.blood_bank.model.User;


import java.util.List;


public class BloodBankRecyclerAdapter extends RecyclerView.Adapter<com.example.rr.virtual_hospital.nav_drawer.blood_bank.adapter.eachUser> {
    List<com.example.rr.virtual_hospital.nav_drawer.blood_bank.model.User> users;
    Context context;

    public BloodBankRecyclerAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public com.example.rr.virtual_hospital.nav_drawer.blood_bank.adapter.eachUser onCreateViewHolder(ViewGroup parent, int viewType) {
        View thisview = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_recycler, parent, false);
        return new com.example.rr.virtual_hospital.nav_drawer.blood_bank.adapter.eachUser(thisview, users);
    }

    @Override
    public void onBindViewHolder(com.example.rr.virtual_hospital.nav_drawer.blood_bank.adapter.eachUser holder, int position) {
        User user = users.get(position);

        holder.name.setText(user.getName());
        holder.mobileNumber.setText(user.getCotactNumber());
        holder.bloodGroup.setText(user.getBloodGroup());
        holder.division.setText(user.getDivision());
        if(user.getLastDonated()!=null){
            holder.lastDonated.setText(user.getLastDonated());
            Log.d("holderdata",user.getLastDonated());
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
class eachUser extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView name, bloodGroup, mobileNumber, lastDonated, division;

    List<User> users;

    public eachUser(View itemView, List<User> users) {
        super(itemView);
        this.users = users;
        name = (TextView) itemView.findViewById(R.id.recycler_each_name);
        bloodGroup = (TextView) itemView.findViewById(R.id.recycler_each_blood_group);
        mobileNumber = (TextView) itemView.findViewById(R.id.recycler_each_mobile);
        //lastDonated = (TextView) itemView.findViewById(R.id.recycler_each_last_donated);
        division = (TextView) itemView.findViewById(R.id.recycler_each_division);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(final View view) {
        final AlertDialog.Builder singleUser = new AlertDialog.Builder(view.getContext())
                .setTitle(users.get(getAdapterPosition()).getName())
                .setMessage("Mobile Number: " + users.get(getAdapterPosition()).getCotactNumber())
                .setPositiveButton("call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent caller = new Intent(Intent.ACTION_DIAL);
                        caller.setData(Uri.parse("tel:" + users.get(getAdapterPosition()).getCotactNumber()));
                        view.getContext().startActivity(caller);
                    }
                })
                .setNegativeButton("done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        singleUser.show();
    }
}

