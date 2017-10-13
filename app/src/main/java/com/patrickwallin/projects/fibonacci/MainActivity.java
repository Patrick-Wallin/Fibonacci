package com.patrickwallin.projects.fibonacci;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editText) public EditText mInputEditText;
    @BindView(R.id.button) public Button mGoButton;
    @BindView(R.id.result_text_view) public TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mInputEditText.setText("0");

        mInputEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (i == KeyEvent.KEYCODE_ENTER)) {
                    mGoButton.performClick();
                    return true;
                }
                return false;
            }
        });
        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processFibonacciCalculation();
            }
        });
    }

    private void processFibonacciCalculation() {
        if(mInputEditText.getText().toString().isEmpty()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Please enter some numbers.");
            alertDialogBuilder.show();
        }else {
            int inputValue = Integer.valueOf(mInputEditText.getText().toString().trim());
            long result = calculateFibonacciBasedOnIndex(inputValue);
            if(result < 0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("Result number is too big to fit into LONG type.");
                alertDialogBuilder.show();
            }else
                mResultTextView.setText(String.valueOf(result));
        }
    }

    private long calculateFibonacciBasedOnIndex(int inputIndex) {
        long result = (inputIndex == 0 ? 0 : 1);

        long previousValue = 0;
        long currentValue = (inputIndex == 0 ? 0 : 1);

        for(int i = 2; i <= inputIndex; i++) {
            result = previousValue + currentValue;
            previousValue = currentValue;
            currentValue = result;
        }

        return result;
    }
}
