package com.example.sbufifthaptauthen;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;

    Button buttonLogout;

    TextView textViewUserEmail;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();
        buttonLogout = findViewById(R.id.logout_button);
        textViewUserEmail = findViewById(R.id.hello_user);


        user = auth.getCurrentUser(); // GET CURRENT LOGGED IN USER

        //IF NO USER IS LOGGED IN, REDIRECT TO LOGINACTIVITY
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity2.class);
            startActivity(intent);
            finish();
        } else {
            //IF NO USER IS LOGGED IN, DISPLAY THEIR EMAIL
            textViewUserEmail.setText(user.getEmail());
        }
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();

                Intent intent = new Intent(getApplicationContext(), LoginActivity2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}