package dreamso.smart_worker;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dreamso.smart_worker.models.Service;

public class ServiceListActivity extends AppCompatActivity {
    EditText search_edit_text;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<String> category;
    ArrayList<String> title;
    ArrayList<String> mobile;
    ArrayList<String> address;
    SearchAdapter searchAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicelist);

        search_edit_text = (EditText) findViewById(R.id.search_field);
        recyclerView = (RecyclerView) findViewById(R.id.result_list);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        /*
        * Create a array list for each node you want to use
        * */
        category = new ArrayList<>();
        title = new ArrayList<>();
        mobile = new ArrayList<>();
        address = new ArrayList<>();

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                } else {
                    /*
                    * Clear the list when editText is empty
                    * */
                    category.clear();
                    title.clear();
                    mobile.clear();
                    address.clear();
                    recyclerView.removeAllViews();
                }
            }
        });
    }

    private void setAdapter(final String searchedString) {
        databaseReference.child("Service").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                * Clear the list for every new search
                * */
                category.clear();
                title.clear();
                mobile.clear();
                address.clear();
                recyclerView.removeAllViews();

                int counter = 0;

                /*
                * Search all users for matching searched string
                * */
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    String catogry_txt = snapshot.child("category").getValue(String.class);
                    String title_txt = snapshot.child("jobtitle").getValue(String.class);
                    String mobile_txt = snapshot.child("mobile").getValue(String.class);
                    String address_txt = snapshot.child("address").getValue(String.class);


                    if (title_txt.toLowerCase().contains(searchedString.toLowerCase())) {
                        category.add(catogry_txt);
                        title.add(title_txt);
                        mobile.add(mobile_txt);
                        mobile.add(address_txt);
                        counter++;
                    } else if (address_txt.toLowerCase().contains(searchedString.toLowerCase())) {
                        category.add(catogry_txt);
                        title.add(title_txt);
                        mobile.add(mobile_txt);
                        mobile.add(address_txt);
                        counter++;
                    }else if (catogry_txt.toLowerCase().contains(searchedString.toLowerCase())) {
                        category.add(catogry_txt);
                        title.add(title_txt);
                        mobile.add(mobile_txt);
                        mobile.add(address_txt);
                        counter++;
                    }

                    /*
                    * Get maximum of 15 searched results only
                    * */
                    if (counter == 15)
                        break;
                }

                searchAdapter = new SearchAdapter(ServiceListActivity.this, category, title, mobile,address);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
