package com.bb.fourstarhotel.presenter;

import android.util.Log;

import androidx.room.Room;

import com.bb.fourstarhotel.MainActivity;
import com.bb.fourstarhotel.database.HotelDB;
import com.bb.fourstarhotel.database.HotelRoomEntity;
import com.bb.fourstarhotel.database.UserEntity;

import java.util.List;

public class RoomPresenter implements Contract.Presenter {

    private Contract.View mainView;
    private HotelDB hotelDB;
    private UserEntity currentUser;

    public RoomPresenter(Contract.View mainView){
        this.mainView = mainView;

        hotelDB = Room.databaseBuilder(((MainActivity) mainView).getApplicationContext(), HotelDB.class, "hotels.db")
                .allowMainThreadQueries()
                .build();

//        for(int i = 0; i < 10; i++){
//            hotelDB.getHotelDao().addNewRoom(new HotelRoomEntity((i+1)+"", true));
//        }

        //hotelDB.getHotelDao().addNewUser(new UserEntity("Dan", "password"));

        List<HotelRoomEntity> HotelRoomList = hotelDB.getHotelDao().getAllAvailableRoom();
        Log.d("TAG_X", "Hotel Room Size: " + HotelRoomList.size());
        Log.d("TAG_X", "Hotel Room Number: " + HotelRoomList.get(0).getRoomNumber());
        Log.d("TAG_X", "Hotel Room Availability: " + HotelRoomList.get(0).isRoomAvailable());


    }

    @Override
    public void loginUser(String userName, String userPassword) {
        currentUser = hotelDB.getHotelDao().loginSelect(userName, userPassword);

        if(currentUser == null)
            mainView.userLoginFailed();
        else
            mainView.userLoginSuccess();
    }

    @Override
    public void logoutUser() {
        currentUser = null;
        mainView.userLoggedOut();
    }

    @Override
    public UserEntity getUserInstance() {
        return currentUser;
    }
}
