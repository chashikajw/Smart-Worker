package dreamso.smart_worker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by admin on 5/20/2018.
 */

public class AddServiceActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String TAG = "AddServiceActivity";

    private static EditText category, title, mobileNumber, description,
            address;

    private static String categorySt, titleSt, mobileNumberSt, descriptionSt,
            addressSt;

    ListView lst;
    String categorylst[] = {"Category","Cleaning", "Shoping","Day care" ,"Repairing","Other"};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addservice);

        Button btnSetLocation = (Button) findViewById(R.id.setlocationBtn);

        //category = (EditText) findViewById(R.id.category);
        title = (EditText) findViewById(R.id.title);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        description = (EditText)findViewById(R.id.description);
        address = (EditText) findViewById(R.id.address);

        Spinner mSpinner = (Spinner) findViewById(R.id.spinner);
        lst = (ListView) findViewById(R.id.lst_view);
        ArrayAdapter<String> arrayaspter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,categorylst);
        arrayaspter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(arrayaspter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);


                String selectedItem = parent.getItemAtPosition(position).toString();
                categorySt = selectedItem;
                if(selectedItem.equals("Add new category"))
                {
                    // do your stuff
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        Toolbar mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        btnSetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //categorySt = category.getText().toString();
                titleSt = title.getText().toString();
                mobileNumberSt = mobileNumber.getText().toString();
                descriptionSt = description.getText().toString();
                addressSt = address.getText().toString();

                Intent intent = new Intent(getBaseContext(), SetLocationActivity.class);
                intent.putExtra("category", categorySt);
                intent.putExtra("title", titleSt);
                intent.putExtra("mobileNumber", mobileNumberSt);
                intent.putExtra("description", descriptionSt);
                intent.putExtra("address", addressSt);


                startActivity(intent);

            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
}
