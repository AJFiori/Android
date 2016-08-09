package com.example.anthonyjfiori.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Lab3 extends AppCompatActivity {

//Sets REQUEST_CODE
    public static final int REQUEST_CODE = 10;

//Declares variables
    //Variable to GET firstOrder
    private Order firstOrder;

    //Array for saved orders
    private ArrayList<Order> itemOrder = new ArrayList<Order>();

    //Differnt Choclate types in Spinner
    private List<String> candyList = new ArrayList<String>();

    //Array to pull and display firstOrder
    private ArrayAdapter<String> dataAdapter;

//Text Fields
    private EditText
             fiName
            ,laName
            ,nBars;

    //Save Button
    private Button saveButton;

    //Spinner
    private Spinner tyOfBars;

    //Radio Button
    private RadioButton normalShipType,expeditedShipType;

    //Output Text
    private TextView oResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//sets textbox variables by id
        fiName = (EditText) findViewById(R.id.txtFirstName);
        laName = (EditText) findViewById(R.id.txtLastName);
        nBars = (EditText) findViewById(R.id.txtNofBars);
        oResults = (TextView)findViewById(R.id.txtOrderResults);
//sets radio buttons variable by id
        normalShipType = (RadioButton) findViewById(R.id.rbNShipment);
        expeditedShipType = (RadioButton)findViewById(R.id.rbEpedited);


//Initializes button
        saveButton = (Button)findViewById(R.id.btnSave);
        saveButton.setOnClickListener(sButton);

//sets spinner variable by id
        tyOfBars = (Spinner)findViewById(R.id.spTypeChocolate);

        candyList.add("Please Select a Candy Bar:");
        candyList.add("Dark Chocolate");
        candyList.add("Mint Chocolate");
        candyList.add("Milk Chocolate");

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,candyList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tyOfBars.setAdapter(dataAdapter);

    }

    //Save Button method
    private View.OnClickListener sButton = new View.OnClickListener(){
        public void onClick(View V){

            String fName = fiName.getText().toString();
            String lName = laName.getText().toString();
            int numOfBars = Integer.parseInt(nBars.getText().toString());
            Boolean typeOfShipment = normalShipType.isChecked();
            String typeOfChocolate = tyOfBars.getSelectedItem().toString();

            if(fName.isEmpty() && lName.isEmpty()){
                Toast.makeText(getApplicationContext(),
                        "ERROR - Provide First & Last Name", Toast.LENGTH_LONG).show();
            }
            else {
                //Adds first order then loops through to keep adding orders
                Order firstOrder = new Order(fName, lName, typeOfChocolate, numOfBars, typeOfShipment);
                itemOrder.add(firstOrder);
                oResults.setText("Order Added, There are now " + itemOrder.size() + " Orders");
            }
        }
    };

    //Takes name from XML (android:onClick="GoToResults") & Pushes information to Results window
    public void GoToResults(View view){
        EditText myFirstName = (EditText)findViewById(R.id.txtFirstName);
        EditText myLastName = (EditText)findViewById(R.id.txtLastName);
        Spinner myTypeOfChocolate = (Spinner)findViewById(R.id.spTypeChocolate);

        String messageFirstName = myFirstName.getText().toString();
        String messageLastName = myLastName.getText().toString();
        String messageTypeOfChocolate = myTypeOfChocolate.getSelectedItem().toString();

        Intent i = new Intent(this,Results.class);
        i.putExtra("mykey", messageFirstName + " " + messageLastName + " " + messageTypeOfChocolate);
        startActivityForResult(i,REQUEST_CODE);
    }

//Pushes results window information back to main window
@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
        if(data.hasExtra("results")){
            int result = data.getExtras().getInt("results");
            if(result >=0){
                oResults.setText("Number of Orders: " + result);
            }
        }
    }
}

//Creates Navigation Menu
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lab3, menu);
        return true;
    }
@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

    //Menu fields
    switch (id){

        //New menu field(Clears all fields)
        case R.id.create_new:
            //Text entry fields
            EditText firstNameText = (EditText)findViewById(R.id.txtFirstName);
            EditText lastNameText = (EditText)findViewById(R.id.txtLastName);
            EditText numOfBarsText = (EditText)findViewById(R.id.txtNofBars);
            TextView results = (TextView)findViewById(R.id.txtOrderResults);
            firstNameText.setText("");
            lastNameText.setText("");
            numOfBarsText.setText("0");
            results.setText("");

            //Type of chocolate Spinner
            Spinner ddlTypeOfChocolate = (Spinner)findViewById(R.id.spTypeChocolate);
            ddlTypeOfChocolate.setSelection(0);

            //Shipment Type
            RadioGroup shipmentType = (RadioGroup)findViewById(R.id.radioGroup);

            shipmentType.clearCheck();
            break;

        //Double Order Menu
        case R.id.create_doubleOrder:

            EditText doubleBarsText = (EditText)findViewById(R.id.txtNofBars);
            //Takes input value and doubles it
            int valueOne = Integer.parseInt(doubleBarsText.getText().toString());
            int sumOfValues = valueOne * 2;
            doubleBarsText.setText(String.valueOf(sumOfValues));
            break;

        //New menu that only clears text fields
        case R.id.create_newText:
            //Text entry fields
            EditText firstNameTextOnly = (EditText)findViewById(R.id.txtFirstName);
            EditText lastNameTextOnly = (EditText)findViewById(R.id.txtLastName);
            EditText numOfBarsTextOnly = (EditText)findViewById(R.id.txtNofBars);
            TextView resultsTextOnly = (TextView)findViewById(R.id.txtOrderResults);
            firstNameTextOnly.setText("");
            lastNameTextOnly.setText("");
            numOfBarsTextOnly.setText("0");
            resultsTextOnly.setText("");
            break;

        //Get First Order Menu
        case R.id.create_getFirstOrder:
            //calls variable to get the firstOrder from itemOrder
            firstOrder = itemOrder.get(0);

            //grabs the information from first order
            fiName.setText(firstOrder.getfName());
            laName.setText(firstOrder.getlName());

            String typeOfBars = firstOrder.getTypeOfChocolate();

            int positionOfType = dataAdapter.getPosition(typeOfBars);

            tyOfBars.setSelection(positionOfType);
            nBars.setText(String.valueOf(firstOrder.getNofBars()));

            if(firstOrder.isTypeOfShipment()){
                expeditedShipType.setChecked(true);
            }
            else{
                normalShipType.setChecked(true);
            }

            break;
    }
        return super.onOptionsItemSelected(item);
    }
}
