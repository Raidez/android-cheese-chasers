package com.mga.cheesechasers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity implements View.OnClickListener {
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mButton = (Button)findViewById(R.id.newgame);
        mButton.setOnClickListener(this);

    }

    public void onClick(View v) {
        if(v == mButton) {
            Intent intention = new Intent(this,MainActivity.class);
            startActivity(intention);
        }
    }

}
