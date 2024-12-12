package com.example.doitmission_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    EditText inputMessage;
    TextView inputCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMessage = findViewById(R.id.inputMessage);
        inputCount = findViewById(R.id.inputCount);

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = inputMessage.getText().toString();
                Toast.makeText(getApplicationContext(), "Sending now\n\n"+message, Toast.LENGTH_LONG).show();;
            }
        });

        Button closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                byte[] bytes = null;
                try{
                    bytes = s.toString().getBytes("KSC5601");
                    int strCount = bytes.length;
                    inputCount.setText(strCount+" / 80 Byte");
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable strEditable) {
                String str = strEditable.toString();
                try {
                    byte[] strBytes = str.getBytes("KSC5601");
                    if (strBytes.length>80){
                        strEditable.delete(strEditable.length()-2, strEditable.length()-1);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        inputMessage.addTextChangedListener(watcher);
    }
}
