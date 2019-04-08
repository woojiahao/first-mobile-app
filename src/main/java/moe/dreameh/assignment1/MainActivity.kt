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
import java.util.*

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var adviceList: MutableList<Advice> = ArrayList()

    private val CHILD_ACTIVITY_RESULT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Fab actions
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { startChildActivity() }

        // RecyclerView settings
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView!!.setHasFixedSize(true)

        // using a linear layout manager
        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager

        // Specify adapter for recyclerView
        mAdapter = AdviceAdapter(adviceList)
        recyclerView!!.adapter = mAdapter

        // Seed the advice list
        seedAdviceList()
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
                val toast = Toast.makeText(applicationContext, "No advice was added.",
                        Toast.LENGTH_LONG)
                toast.show()
            } else if (resultCode == Activity.RESULT_OK) {
                val toast = Toast.makeText(applicationContext, "A new advice has been" + " added.", Toast.LENGTH_LONG)
                toast.show()

                adviceBundle = data!!.extras

                adviceList.add(Advice(adviceBundle!!.getString("AUTHOR"),
                        adviceBundle.getString("CATEGORY"), adviceBundle.getString("CONTENT")))

                mAdapter!!.notifyDataSetChanged()

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
                recyclerView!!.swapAdapter(AdviceAdapter(adviceList), true)
                mAdapter!!.notifyDataSetChanged()
                return true
            }
            R.id.lifestyle_menu -> {
                recyclerView!!.swapAdapter(AdviceAdapter(getCategoryItems("Lifestyle")), true)
                mAdapter!!.notifyDataSetChanged()
                return true
            }
            R.id.misc_menu -> {
                recyclerView!!.swapAdapter(AdviceAdapter(getCategoryItems("Miscellaneous")), true)
                mAdapter!!.notifyDataSetChanged()
                return true
            }
            R.id.tech_menu -> {
                recyclerView!!.swapAdapter(AdviceAdapter(getCategoryItems("Technology")), true)
                mAdapter!!.notifyDataSetChanged()
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

    private fun seedAdviceList() {
        adviceList.add(Advice("Oscar Unas", "Lifestyle", "Spicy jalapeno " +
                "bacon ipsum dolor amet strip steak salami pancetta filet" +
                " mignon t-bone ham shankle bresaola frankfurter rump."))

        adviceList.add(Advice("Iron Man", "Technology", "Spicy jalapeno " +
                "bacon ipsum dolor amet strip steak salami pancetta filet" +
                " mignon t-bone ham shankle bresaola frankfurter rump."))

        adviceList.add(Advice("Green Lantern", "Miscellaneous", "Spicy jalapeno " +
                "bacon ipsum dolor amet strip steak salami pancetta filet" +
                " mignon t-bone ham shankle bresaola frankfurter rump."))

        mAdapter!!.notifyDataSetChanged()

    }
}
