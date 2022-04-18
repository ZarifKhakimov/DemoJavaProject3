package com.example.demojavaproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demojavaproject.model.Member;
import com.example.demojavaproject.model.User;



public class MainActivity extends AppCompatActivity {
    static final String TAG = MainActivity.class.toString();

    TextView tv_home;//int LAUNCH_DETAIL = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    ActivityResultLauncher<Intent> detailLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        User user = new User(14, "Zarifjon");
                        Log.d(TAG, "user");
                        tv_home.setText(user.toString());
                    }
                }
            });

    void initViews(){
       tv_home = findViewById(R.id.tv_home);

        Button b_detail = findViewById(R.id.b_detail);
        b_detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                User user = new User(14, "SENATOR");
                openDetailActivity(user);
            }
        });
       if(getIntent().hasExtra("member")){
           Member member = (Member)getIntent().getSerializableExtra("member");
           Log.d(TAG, member.toString());

           tv_home.setText(member.toString());
       }
    }

    void openDetailActivity(User user){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("user", user);
        setResult(Activity.RESULT_OK, intent);
        detailLauncher.launch(intent);

    }
}
