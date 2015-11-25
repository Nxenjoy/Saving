package nl.s5630213023.saving;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener{

    ListView listView;
    static ArrayList<item> arraySaving = new ArrayList<item>();
    SavingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        Button btnRefresh = (Button) findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new SavingAdapter(this, arraySaving);
        listView.setAdapter(adapter);
        btnShow();

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
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnAdd:
                Intent SecondActivity = new Intent(this,SecondActivity.class);
                startActivity(SecondActivity);
                break;

            case R.id.btnRefresh:
                listView.setAdapter(adapter);
                arraySaving.clear();
                btnShow();
                break;
        }
    }

    private  void btnShow(){
        Uri u = Uri.parse("content://SavingDB");
        Cursor c = getContentResolver().query(u,null,null,null,null);
        while (c.moveToNext()){
            arraySaving.add(new item (c.getString(1).toString(), Integer.parseInt(c.getString(2).toString()),Integer.parseInt(c.getString(3).toString())));
        }

    }

        private void setLastID(){
            Uri u = Uri.parse("content://SavingDB");
            String projection[]={"max(id) as pid"};
            Cursor c = getContentResolver().query(u,projection,null,null, null);
            c.moveToNext();
            int id = Integer.parseInt(c.getString(0))+1;

        }
    private  void btnDelete(){
        Uri u = Uri.parse("content://SavingDB");
        String selection = "id = ?";
        //String selectArg[] = {etID.getText().toString()};
        //int r = getContentResolver().delete(u,selection,selectArg);
        //Toast.makeText(getApplicationContext(), "Delete " + r + " record(s) completed", Toast.LENGTH_SHORT).show();*/
        setLastID();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        arraySaving.remove(listView.getItemAtPosition(position));
        adapter = new SavingAdapter(this, arraySaving);
        listView.setAdapter(adapter);

    }
}
