package moe.dreameh.assignment1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddAdvice : AppCompatActivity() {
    private var bundle = Bundle()
    private val category = arrayOfNulls<String>(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_advice)

        // Initialization of the spinner
        val spinner = findViewById<Spinner>(R.id.category_spinner)

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item)

        // Specifiy the layout to use when the list of choices appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                category[0] = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Nothing will be added here.
            }
        }


        // Set cancel result
        val buttonCancel = findViewById<Button>(R.id.button_cancel)
        buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        val buttonOk = findViewById<Button>(R.id.button_create)
        buttonOk.setOnClickListener {
            val authorName = findViewById<EditText>(R.id.enter_name)
            val contentAmount = findViewById<EditText>(R.id.enter_content)


            when {
                authorName.text.isEmpty() -> authorName.error = "Field cannot be left blank."
                contentAmount.text.isEmpty() -> contentAmount.error = "Field cannot be left blank."
                else -> {
                    addAdviceBundle()

                    val resultIntent = Intent().putExtras(bundle)
                    //resultIntent.putExtras(bundle)
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }

    private fun addAdviceBundle() {
        // Finding both EditTexts
        val authorName = findViewById<EditText>(R.id.enter_name)
        val contentAmount = findViewById<EditText>(R.id.enter_content)


        bundle.putString("AUTHOR", authorName.text.toString())
        bundle.putString("CONTENT", contentAmount.text.toString())
        bundle.putString("CATEGORY", category[0])
    }


}
