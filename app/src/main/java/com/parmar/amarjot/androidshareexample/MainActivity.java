package com.parmar.amarjot.androidshareexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // https://developer.android.com/training/sharing/receive
        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        }

        setupShareButton();
    }

    private void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            EditText editTextBox = findViewById(R.id.textViewMsg);
            editTextBox.setText(sharedText);
        }
    }

    private void setupShareButton() {

        Button share = findViewById(R.id.buttonShare);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");

                EditText editTextBox = findViewById(R.id.textViewMsg);
                String msgToShare = editTextBox.getText().toString();
                sendIntent.putExtra(Intent.EXTRA_TEXT, msgToShare);

                startActivity(Intent.createChooser(sendIntent, "Share using"));
                // https://developer.android.com/training/sharing/send
            }
        });
    }
}
