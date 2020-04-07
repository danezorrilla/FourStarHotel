package com.bb.fourstarhotel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bb.fourstarhotel.adapter.HotelRoomAdapter;
import com.bb.fourstarhotel.database.GuestEntity;
import com.bb.fourstarhotel.database.HotelDB;
import com.bb.fourstarhotel.database.HotelRoomEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginFragment extends Fragment implements HotelRoomAdapter.HotelRoomInterface {

    private HotelDB hotelDB;

    private List hotelRoomList = new ArrayList<HotelRoomEntity>();

    @BindView(R.id.new_guest_name)
    EditText newGuestName;

    @BindView(R.id.new_guest_room)
    TextView newGuestRoomNum;

    @BindView(R.id.available_room_list)
    RecyclerView availableRoomList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        hotelDB = Room.databaseBuilder(getContext(), HotelDB.class, "hotels.db")
                .allowMainThreadQueries()
                .build();

        setUpHotelRoomAdapter();
    }

    @OnClick(R.id.add_new_guest)
    public void addNewGuest(View view){
        String guestName = newGuestName.getText().toString();
        String guestRoomNum = newGuestRoomNum.getText().toString();
        //System.out.println("Guest Name: " + guestName + " Guest Room: " + guestRoomNum);
        newGuestName.setText("");
        newGuestRoomNum.setText("");

        hotelDB.getHotelDao().addNewGuest(new GuestEntity(guestName, guestRoomNum));

        // UPDATE HotelRoom SET isAvailable = 0 WHERE roomNumber = {UserInput}
        hotelDB.getHotelDao().changeAvailabilty(guestRoomNum);
    }

    @Override
    public void getRoom(HotelRoomEntity getRoom) {
        String getRoomNumber = getRoom.getRoomNumber() + "";
        System.out.println(getRoomNumber);
        newGuestRoomNum.setText(getRoomNumber);
    }

    @OnClick(R.id.user_logout)
    public void logOut(){
        ((MainActivity)getContext()).logOut();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    public void setUpHotelRoomAdapter(){
        HotelRoomAdapter hotelRoomAdapter = new HotelRoomAdapter(hotelDB.getHotelDao().getAllAvailableRoom(), this);
        availableRoomList.setLayoutManager(new LinearLayoutManager(getContext()));
        availableRoomList.setAdapter(hotelRoomAdapter);
    }
}
