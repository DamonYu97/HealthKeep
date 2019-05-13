package com.example.damon.healthkeep;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button signUpButton;
    private String username,password;
    private OkHttpClient okHttpClient;
    private String baseUrl = LoginActivity.baseUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ImageButton backButton = (ImageButton)findViewById(R.id.sign_up_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //initialize
        usernameEdit = (EditText)findViewById(R.id.sign_up_username);
        passwordEdit = (EditText)findViewById(R.id.sign_up_password);
        signUpButton = (Button)findViewById(R.id.sign_up_button);
        //sign up
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get username and password
                username = usernameEdit.getText().toString().trim();
                password = passwordEdit.getText().toString().trim();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(SignUpActivity.this,"用户名或密码为空",Toast.LENGTH_SHORT).show();
                } else {
                    //submit username and password
                    okHttpClient = new OkHttpClient();
                    Request.Builder builder=new Request.Builder();
                    final Request request = builder.get().url(baseUrl+"login?username="+username+"&password="+password).build();
                    Call call=okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("Login Failure!",e.getMessage());
                            Looper.prepare();
                            Toast.makeText(SignUpActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String result = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (!result.equals("")) {
                                        Toast.makeText(SignUpActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                        //jump into Main page
                                        Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}
