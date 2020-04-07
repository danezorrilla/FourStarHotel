package com.bb.fourstarhotel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;

import com.bb.fourstarhotel.adapter.GuestAdapter;
import com.bb.fourstarhotel.database.HotelDB;
import com.bb.fourstarhotel.presenter.Contract;
import com.bb.fourstarhotel.presenter.RoomPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Contract.View{

    private Contract.Presenter mainPresenter;

    private LoginFragment loginFragment;

    private HotelDB hotelDB;

    @BindView(R.id.login_user_name)
    EditText loginUserName;

    @BindView(R.id.login_user_password)
    EditText loginUserPassword;

    @BindView(R.id.main_guestList)
    RecyclerView guestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new RoomPresenter(this);
        loginFragment = new LoginFragment();

        hotelDB = Room.databaseBuilder(this, HotelDB.class, "hotels.db")
                .allowMainThreadQueries()
                .build();

        setUpAdapter();
    }

    @OnClick(R.id.login_login)
    public void loginClick(View view){
        String username = loginUserName.getText().toString();
        String password = loginUserPassword.getText().toString();
        mainPresenter.loginUser(username, password);

        loginUserName.setText("");
        loginUserPassword.setText("");
    }

    public void logOut(){
        mainPresenter.logoutUser();
    }

    @Override
    public void userLoginSuccess() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.user_frame, loginFragment)
                .commit();
    }

    @Override
    public void userLoginFailed() {
        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme))
                .setTitle("Login failed")
                .setMessage("Username or password incorrect. Please re-enter")
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        loginUserName.setText("");
                        loginUserPassword.setText("");
                    }
                })
                .create()
                .show();
    }

    @Override
    public void userLoggedOut() {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(loginFragment)
                .commit();
    }

    private void setUpAdapter(){
        GuestAdapter guestAdapter = new GuestAdapter(hotelDB.getHotelDao().getAllGuest());
        guestList.setLayoutManager(new LinearLayoutManager(this));
        guestList.setAdapter(guestAdapter);
    }

}
