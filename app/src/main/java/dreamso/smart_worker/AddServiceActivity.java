package dreamso.smart_worker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by admin on 5/20/2018.
 */

public class AddServiceActivity extends AppCompatActivity {

    public static final String TAG = "AddServiceActivity";

    private static EditText category, title, mobileNumber, description,
            address;

    private static String categorySt, titleSt, mobileNumberSt, descriptionSt,
            addressSt;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addservice);

        Button btnSetLocation = (Button) findViewById(R.id.setlocationBtn);

        category = (EditText) findViewById(R.id.category);
        title = (EditText) findViewById(R.id.title);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        description = (EditText)findViewById(R.id.description);
        address = (EditText) findViewById(R.id.address);







        btnSetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                categorySt = category.getText().toString();
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
}
