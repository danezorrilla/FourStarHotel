package com.bb.fourstarhotel.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {HotelRoomEntity.class, GuestEntity.class, UserEntity.class})
public abstract class HotelDB extends RoomDatabase {
    public abstract HotelDAO getHotelDao();
}
