package com.bb.fourstarhotel.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "HotelRoom")
public class HotelRoomEntity {

    @PrimaryKey(autoGenerate = true)
    private int roomId;

    @ColumnInfo(name = "roomNumber")
    private String roomNumber;

    @ColumnInfo(name = "roomAvailable")
    private boolean roomAvailable;

    public HotelRoomEntity(int roomId, String roomNumber, boolean roomAvailable) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomAvailable = roomAvailable;
    }

    @Ignore
    public HotelRoomEntity(String roomNumber, boolean roomAvailable) {
        this.roomNumber = roomNumber;
        this.roomAvailable = roomAvailable;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isRoomAvailable() {
        return roomAvailable;
    }

    public void setRoomAvailable(boolean roomAvailable) {
        this.roomAvailable = roomAvailable;
    }
}
