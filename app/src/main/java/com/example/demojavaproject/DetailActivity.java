package com.example.demojavaproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demojavaproject.model.Member;
import com.example.demojavaproject.model.User;



public class DetailActivity extends AppCompatActivity {
    static final String TAG = DetailActivity.class.toString();

    TextView tv_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
    }

    ActivityResultLauncher<Intent> detailLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent m = result.getData();
                        Member member = new Member(14,  "Zarifjon");
                        Log.d(TAG, "member");
                        tv_detail.setText(member.toString());
                    }
                }
            });

    void initViews(){
       tv_detail = findViewById(R.id.tv_detail);

        Button b_exit = findViewById(R.id.b_exit);
        b_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Member member = new Member(3, "Big");
                backToFinish(member);
            }
        });
        User user = (User)getIntent().getSerializableExtra("user");
        Log.d(TAG, user.toString());

        tv_detail.setText(user.toString());
    }

    void backToFinish(Member member){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("member", member);
        setResult(Activity.RESULT_OK, intent);
        detailLauncher.launch(intent);
        finish();
    }
}
