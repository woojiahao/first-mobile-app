package moe.dreameh.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Advice> adviceList;

    private final int CHILD_ACTIVITY_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Fab actions
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startChildActivity();
            }
        });
        // adviceList initialization
        adviceList = new ArrayList<>();

        // RecyclerView settings
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        // using a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // Specify adapter for recyclerView
        mAdapter = new AdviceAdapter(adviceList);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView,
                new RecyclerViewClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        adviceList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        Toast toast = Toast.makeText(getApplicationContext(), "An advice has been" +
                                " removed from the list.", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }));

        // Seed the advice list
        seedAdviceList();
    }


    private void startChildActivity() {
        Intent intent = new Intent(this, AddAdvice.class);
        startActivityForResult(intent, CHILD_ACTIVITY_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle adviceBundle;

        if(requestCode == CHILD_ACTIVITY_RESULT) {

            if(resultCode == RESULT_CANCELED) {
                Toast toast = Toast.makeText(getApplicationContext(), "No advice was added.",
                        Toast.LENGTH_LONG);
                toast.show();
            } else if(resultCode == RESULT_OK) {
                Toast toast = Toast.makeText(getApplicationContext(), "A new advice has been" +
                        " added.", Toast.LENGTH_LONG);
                toast.show();

                adviceBundle = data.getExtras();

                adviceList.add(new Advice(adviceBundle.getString("AUTHOR"),
                        adviceBundle.getString("CATEGORY"), adviceBundle.getString("CONTENT")));

                mAdapter.notifyDataSetChanged();

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        RecyclerView.Adapter selectedAdapter;

        switch (item.getItemId()) {
            case R.id.all_menu:
                recyclerView.swapAdapter(selectedAdapter = new AdviceAdapter(adviceList), true);
                selectedAdapter.notifyDataSetChanged();
                return true;
            case R.id.lifestyle_menu:
                recyclerView.swapAdapter(selectedAdapter = new AdviceAdapter(getCategoryItems("Lifestyle")), true);
                selectedAdapter.notifyDataSetChanged();
                return true;
            case R.id.misc_menu:
                recyclerView.swapAdapter(selectedAdapter = new AdviceAdapter(getCategoryItems("Miscellaneous")), true);
                selectedAdapter.notifyDataSetChanged();
                return true;
            case R.id.tech_menu:
                recyclerView.swapAdapter(selectedAdapter = new AdviceAdapter(getCategoryItems("Technology")), true);
                selectedAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public List<Advice> getCategoryItems(String category) {
        List<Advice> newlist = new ArrayList<>();

        for(Advice advice : adviceList)
            if (advice.getCategory().equals(category)) {
                newlist.add(advice);
            }

        return newlist;
    }

    private void seedAdviceList() {
        adviceList.add(new Advice("Oscar Unas", "Lifestyle", "Spicy jalapeno " +
                "bacon ipsum dolor amet strip steak salami pancetta filet" +
                " mignon t-bone ham shankle bresaola frankfurter rump."));

        adviceList.add(new Advice("Iron Man", "Technology", "Spicy jalapeno " +
                "bacon ipsum dolor amet strip steak salami pancetta filet" +
                " mignon t-bone ham shankle bresaola frankfurter rump."));

        adviceList.add(new Advice("Green Lantern", "Miscellaneous", "Spicy jalapeno " +
                "bacon ipsum dolor amet strip steak salami pancetta filet" +
                " mignon t-bone ham shankle bresaola frankfurter rump."));

        mAdapter.notifyDataSetChanged();

    }
 }
