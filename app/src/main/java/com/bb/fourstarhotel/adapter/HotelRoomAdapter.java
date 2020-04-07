package com.bb.fourstarhotel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bb.fourstarhotel.R;
import com.bb.fourstarhotel.database.HotelRoomEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotelRoomAdapter extends RecyclerView.Adapter<HotelRoomAdapter.HotelRoomViewHolder> {

    public interface HotelRoomInterface{
        void getRoom(HotelRoomEntity getRoom);
    }

    private List<HotelRoomEntity> hotelRoomList;
    private HotelRoomInterface hotelRoomInterface;


    public HotelRoomAdapter(List<HotelRoomEntity> hotelRoomList, HotelRoomInterface hotelRoomInterface){
        this.hotelRoomList = hotelRoomList;
        this.hotelRoomInterface = hotelRoomInterface;
    }

    @NonNull
    @Override
    public HotelRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.available_room_layout, parent, false);

        return new HotelRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelRoomAdapter.HotelRoomViewHolder holder, int position) {
        holder.roomNumberTextView.setText(hotelRoomList.get(position).getRoomNumber());

        holder.itemView.setOnClickListener(view ->{
            System.out.println("This Room is selected");
            //System.out.println(hotelRoomList.get(position).getRoomId());
            hotelRoomInterface.getRoom(hotelRoomList.get(position));

        });
    }

    @Override
    public int getItemCount() {
        return hotelRoomList.size();
    }


    public class HotelRoomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.room_list_number)
        TextView roomNumberTextView;

        public HotelRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
