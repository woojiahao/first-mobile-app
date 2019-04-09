package moe.dreameh.assignment1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.start_fragment.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var adviceList: MutableList<Advice> = ArrayList()

    private val CHILD_ACTIVITY_RESULT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Adding a fab to start AddAdviceActivity
        fab.setOnClickListener { startChildActivity() }

        // RecyclerView settings
        recycler_view.setHasFixedSize(true)

        // using a linear layout manager
        recycler_view.layoutManager = LinearLayoutManager(this)

        // Specify adapter for recyclerView
        recycler_view.adapter = AdviceAdapter(adviceList)
    }


    private fun startChildActivity() {
        val intent = Intent(this, AddAdvice::class.java)
        startActivityForResult(intent, CHILD_ACTIVITY_RESULT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val adviceBundle: Bundle?

        if (requestCode == CHILD_ACTIVITY_RESULT) {

            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(applicationContext, "No advice was added.",
                        Toast.LENGTH_LONG).show()

            } else if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(applicationContext, "A new advice has been" + " added.", Toast.LENGTH_LONG).show()

                adviceBundle = data!!.extras

                adviceList.add(Advice(adviceBundle!!.getString("AUTHOR"),
                        adviceBundle.getString("CATEGORY"), adviceBundle.getString("CONTENT")))

                recycler_view.adapter?.notifyDataSetChanged()

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        when (item.itemId) {
            R.id.all_menu -> {
                recycler_view.swapAdapter(AdviceAdapter(adviceList), true)
                recycler_view.adapter?.notifyDataSetChanged()
                return true
            }
            R.id.lifestyle_menu -> {
                recycler_view.swapAdapter(AdviceAdapter(getCategoryItems("Lifestyle")), true)
                recycler_view.adapter?.notifyDataSetChanged()
                return true
            }
            R.id.misc_menu -> {
                recycler_view.swapAdapter(AdviceAdapter(getCategoryItems("Miscellaneous")), true)
                recycler_view.adapter?.notifyDataSetChanged()
                return true
            }
            R.id.tech_menu -> {
                recycler_view.swapAdapter(AdviceAdapter(getCategoryItems("Technology")), true)
                recycler_view.adapter?.notifyDataSetChanged()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun getCategoryItems(category: String): MutableList<Advice> {
        val newlist = ArrayList<Advice>()

        for (advice in adviceList)
            if (advice.category == category) {
                newlist.add(advice)
            }

        return newlist
    }

}
