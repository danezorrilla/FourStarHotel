package com.bb.fourstarhotel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bb.fourstarhotel.R;
import com.bb.fourstarhotel.database.GuestEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.GuestViewHolder> {

    private List<GuestEntity> guestList;

    public GuestAdapter(List<GuestEntity> guestList) {
        this.guestList = guestList;
    }

    @NonNull
    @Override

    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_allocation_layout, parent, false);

        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestAdapter.GuestViewHolder holder, int position) {
        holder.guestNameTextView.setText(guestList.get(position).getGuestName());
        holder.guestRoomNumberTextView.setText(guestList.get(position).getGuestRoomNumber());
    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    public class GuestViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.room_all_guest_name)
        TextView guestNameTextView;

        @BindView(R.id.room_all_guest_num)
        TextView guestRoomNumberTextView;

        public GuestViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
