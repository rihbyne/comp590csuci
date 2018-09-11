package com.example.brianthoms.hitme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import android.util.Log;

public class HitMeActivity extends AppCompatActivity {

    private Button mBtn;
    private TextView mTextView;
    String msg = "Android : ";
    private int hitCounter;
    private String bruiseLevelText, ouchMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(msg, "The onCreate() was run.");
        ouchMessage="";
        bruiseLevelText="";
        setContentView(R.layout.activity_hit_me);
        mTextView = (TextView) findViewById(R.id.hitMeTextView);

        if (savedInstanceState != null) {
            ouchMessage = savedInstanceState.getString("ouchMessage");
            hitCounter=savedInstanceState.getInt("hitCounter");
            mTextView.setText(ouchMessage);
        } else {
            hitCounter = 0;
        }
        mBtn = (Button) findViewById(R.id.hitMeButton);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitCounter++;
                if(hitCounter > 30)
                {
                    bruiseLevelText="And now my arm is about to fall off.";
                }
                else if(hitCounter > 20)
                {
                    bruiseLevelText="And now my arm is blue.";
                }
                else if(hitCounter > 10)
                {
                    bruiseLevelText="And now my arm is red.";
                }
                Toast.makeText(HitMeActivity.this, R.string.ouch,
                        Toast.LENGTH_SHORT).show();
                ouchMessage=getResources().getString(R.string.ouch) + "\nI've been hit " +
                        hitCounter + " times. " + "\n" + bruiseLevelText;
                mTextView.setText(ouchMessage);
            }
        });

        Button dontmBtn = (Button) findViewById(R.id.dontHitMeButton);
        dontmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(HitMeActivity.this, R.string.sorry, Toast.LENGTH_SHORT).show();

                hitCounter--;

                if (hitCounter < 0) {
                    hitCounter = 0;
                    bruiseLevelText="You are fit now.";
                }
                else if (hitCounter > 30) {
                    bruiseLevelText="And now my arm is about to fall off";
                }
                else if (hitCounter > 20 & hitCounter < 30) {
                    bruiseLevelText="my arm is blue.";
                }
                else if(hitCounter > 10 & hitCounter < 20)
                {
                    bruiseLevelText="my arm is red.";
                }
                else if(hitCounter > 0 && hitCounter < 10)
                {
                    bruiseLevelText="About to get normal";
                }

                ouchMessage = "Relief: hits reduced to" + hitCounter + '.' + "\n" + bruiseLevelText;

                mTextView.setText(ouchMessage);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("hitCounter", hitCounter);
        outState.putString("ouchMessage", ouchMessage);
    }
}
