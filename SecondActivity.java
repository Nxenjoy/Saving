package nl.s5630213023.saving;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends Activity implements View.OnClickListener {
    EditText etItem;
    EditText etCash;
    int indexCategory = 0;
    static ArrayList<item> arraySaving = MainActivity.arraySaving;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ((Button)findViewById(R.id.btnSave)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnBack)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnFood)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnDrink)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnEntertain)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnEnergy)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnMonthly)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnPartTime)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnLucky)).setOnClickListener(this);
        etItem = ((EditText) findViewById(R.id.item));
        etCash = ((EditText) findViewById(R.id.cash));
        // arraySaving.add(new SavingDB(etItem.getText().toString(), Double.parseDouble(etCash.getText().toString()),category));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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


    @Override
    public void onClick(View v) {
        Intent MainActivity;
            switch (v.getId()) {
                case R.id.btnFood: indexCategory = 1; break;
                case R.id.btnDrink: indexCategory = 2; break;
                case R.id.btnEntertain: indexCategory = 3; break;
                case R.id.btnEnergy: indexCategory = 4; break;
                case R.id.btnMonthly: indexCategory = 5; break;
                case R.id.btnPartTime: indexCategory = 6; break;
                case R.id.btnLucky: indexCategory = 7; break;
                case R.id.btnSave:
                    arraySaving.clear();
                    Toast.makeText(getApplicationContext(),"ITEM : "+etItem.getText().toString()+ " PRICE : " + etCash.getText().toString() + " INDEXCATEGORY : " + indexCategory,Toast.LENGTH_SHORT).show();
                    btnSave();
                    btnClear();
                    break;

                case R.id.btnBack:
                    MainActivity = new Intent(this, MainActivity.class);
                    startActivity(MainActivity);
                    break;
            }
    }

    private  void btnSave(){
        Uri u = Uri.parse("content://SavingDB");
        ContentValues cv = new ContentValues();
        cv.put("item",etItem.getText().toString());
        cv.put("cash",Integer.parseInt(etCash.getText().toString()));
        cv.put("category",indexCategory);
        Uri uri = getContentResolver().insert(u,cv);
    }

    private  void btnClear(){
        etItem.setText("");
        etCash.setText("");
    }
}
