package feed.newsfeed.com.newsfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public EditText emailEdt,passwordEdt;
    public Button sumbitBtn;
    public String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEdt = (EditText) findViewById(R.id.email);
        passwordEdt = (EditText) findViewById(R.id.password);
        sumbitBtn = (Button) findViewById(R.id.login_button);

        sumbitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 email = emailEdt.getText().toString();
                 password = passwordEdt.getText().toString();

                 if(email.equals("mas@mas.com") && password.equals("12345678")){
                     Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                     startActivity(intent);
                 }
                 else{
                     Toast.makeText(LoginActivity.this, "wrong credentials", Toast.LENGTH_SHORT).show();
                 }

            }
        });
    }
}
