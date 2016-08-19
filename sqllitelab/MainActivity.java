package com.example.anthonyjfiori.sqllitelab;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.style.TtsSpan;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText
                 accountID
                ,accountName
                ,accountType;

    private TextView total;
    private ListView lsResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        accountID = (EditText)findViewById(R.id.txtID);
        accountName = (EditText)findViewById(R.id.txtName);
        accountType = (EditText)findViewById(R.id.txtType);
        total = (TextView)findViewById(R.id.tvResults);
        lsResults = (ListView)findViewById(R.id.lsResults);

    }
//Adds account to DB
    public void AddAccount(View view){
    DatabaseHandler dbh = new DatabaseHandler(this);
        dbh.addBankAccount(
                new BankAccount(accountName.getText().toString(),
                        accountType.getText().toString())
        );
    }

//Update Account in DB
    public void updateAccount(View view){
        DatabaseHandler dbh = new DatabaseHandler(this);
        dbh.updateBankAccount(
                new BankAccount(Integer.parseInt(accountID.getText().toString()),
                        accountName.getText().toString(),accountType.getText().toString())
        );
    }
//Delete Account from DB
    public void deleteAccount(View view){
        DatabaseHandler dbh = new DatabaseHandler(this);
        dbh.deleteBankAccount(
                new BankAccount(Integer.parseInt(accountID.getText().toString()),
                        accountName.getText().toString(),accountType.getText().toString())
        );
    }

//Gets All Bank Accounts
    public void GetAllAccounts(View view){
        DatabaseHandler dbh = new DatabaseHandler(this);
        List<BankAccount> myAccounts = dbh.getAllAccounts();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
// adding entries in List
        for (int counter = 0 ; counter < myAccounts.size();counter++) {
            adapter.add(myAccounts.get(counter).getID() + " - " +
                    myAccounts.get(counter).getName() + " - " +
                    myAccounts.get(counter).getType());
        }

        lsResults.setAdapter(adapter);
    }

//Getting Account by Name
    public void GetAccountByName(View view){
        DatabaseHandler dbh = new DatabaseHandler(this);

        BankAccount getAccount = dbh.getBankAccount(accountName.getText().toString());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
// adding entries in List

        adapter.add(getAccount.getName() + " " + getAccount.getType());

        lsResults.setAdapter(adapter);

    }

//Gets the total of all the accounts
    public void GetCountOfAccounts(View view){
        DatabaseHandler dbh = new DatabaseHandler(this);
        int accountTotal = dbh.getBankAccountCount();
        total.setText(Integer.toString(accountTotal));
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
}
