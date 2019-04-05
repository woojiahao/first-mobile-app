package moe.dreameh.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddAdvice extends AppCompatActivity{
    Bundle bundle = new Bundle();
    private final String[] category = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advice);

        // Initialization of the spinner
        Spinner spinner = findViewById(R.id.category_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);

        // Specifiy the layout to use when the list of choices appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    category[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Set cancel result
        final Button buttonCancel = findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        final Button buttonOk = findViewById(R.id.button_create);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText authorName = findViewById(R.id.enter_name);
                EditText contentAmount = findViewById(R.id.enter_content);
                if(authorName.getText().length() == 0) {
                    authorName.setError("Field cannot be left blank.");
                } else if(contentAmount.getText().length() == 0) {
                    contentAmount.setError("Field cannot be left blank.");
                } else {
                    addAdviceBundle();

                    Intent resultIntent = new Intent();
                    resultIntent.putExtras(bundle);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }

    private void addAdviceBundle() {
        // Finding both EditTexts
        EditText authorName = findViewById(R.id.enter_name);
        EditText contentAmount = findViewById(R.id.enter_content);


        bundle.putString("AUTHOR", authorName.getText().toString());
        bundle.putString("CONTENT", contentAmount.getText().toString());
        bundle.putString("CATEGORY", category[0]);
    }


}
