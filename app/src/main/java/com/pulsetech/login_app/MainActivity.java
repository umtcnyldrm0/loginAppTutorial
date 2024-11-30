package com.pulsetech.login_app;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtTextUsername = findViewById(R.id.editTextUsername);
        EditText edtTextPassword = findViewById(R.id.editTextPassword);
        Button btnSignUp = findViewById(R.id.buttonSignup);





        try {
            database = this.openOrCreateDatabase("UserInfo", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS Informations (username TEXT, pass TEXT)");
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSignUp.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim));
                String username = edtTextUsername.getText().toString();
                String password = edtTextPassword.getText().toString();

                if (!username.isEmpty() && !password.isEmpty()) {
                    database.execSQL("INSERT INTO Informations (username, pass) VALUES ('" + username + "', '" + password + "')");
                    Toast.makeText(getApplicationContext(), "Eklendi", Toast.LENGTH_SHORT).show();

                    // Log mesajı olarak veri eklendiğini göster
                    Log.d("Veritabanı", "Yeni kullanıcı eklendi: Kullanıcı adı: " + username + ", Şifre: " + password);
                } else {
                    Toast.makeText(getApplicationContext(), "Kullanıcı adı ve şifre gereklidir", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}
