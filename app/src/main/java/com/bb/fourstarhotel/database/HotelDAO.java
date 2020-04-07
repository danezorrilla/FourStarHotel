package com.bb.fourstarhotel.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HotelDAO {

    // Guest Table
    @Insert
    void addNewGuest(GuestEntity... newGuest);

    @Query("SELECT * FROM Guests")
    List<GuestEntity> getAllGuest();

    // Hotel Room Table
    @Query("SELECT * FROM HotelRoom WHERE roomAvailable = 1 ")
    List<HotelRoomEntity> getAllAvailableRoom();

    @Insert
    void addNewRoom(HotelRoomEntity... newRoom);

    @Query("UPDATE HotelRoom SET roomAvailable = 0 WHERE roomNumber = :roomNumber")
    void changeAvailabilty(String roomNumber);

    @Update
    void updateValue(HotelRoomEntity hotelRoomEntity);

    // User Table
    @Insert
    void addNewUser(UserEntity... newUser);

    @Query("SELECT * FROM Users WHERE userName = :userName AND userPassword = :userPassword")
    UserEntity loginSelect(String userName, String userPassword);

}
