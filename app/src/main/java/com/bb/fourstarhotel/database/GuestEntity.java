package com.bb.fourstarhotel.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Guests")
public class GuestEntity {

    @PrimaryKey(autoGenerate = true)
    private int guestId;

    @ColumnInfo(name = "guestName")
    private String guestName;

    @ColumnInfo(name = "guestRoomNumber")
    private String guestRoomNumber;

    public GuestEntity(int guestId, String guestName, String guestRoomNumber) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.guestRoomNumber = guestRoomNumber;
    }

    @Ignore
    public GuestEntity(String guestName, String guestRoomNumber) {
        this.guestName = guestName;
        this.guestRoomNumber = guestRoomNumber;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestRoomNumber() {
        return guestRoomNumber;
    }

    public void setGuestRoomNumber(String guestRoomNumber) {
        this.guestRoomNumber = guestRoomNumber;
    }
}
