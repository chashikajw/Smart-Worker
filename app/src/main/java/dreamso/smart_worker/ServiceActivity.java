package dreamso.smart_worker;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class ServiceActivity extends AppCompatActivity {

    public static final String TAG = "ServiceActivity";
    public static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        if(isServicesOK()){
            init();
        }
    }



    private void init(){
        Button btnMap = (Button) findViewById(R.id.btnMap);
        Button btnaddjob = (Button) findViewById(R.id.AddJobs);

        btnaddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, AddServiceActivity.class);
                startActivity(intent);

            }
        });

       btnMap.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(ServiceActivity.this, MapActivity.class);
               startActivity(intent);

           }
       });


    }

    public boolean isServicesOK(){
        Log.d(TAG,"isServiceOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(ServiceActivity.this);

        if(available== ConnectionResult.SUCCESS){
            //everything ia fine
            Log.d(TAG,"isServiceOK: Google play service is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //error occurd but we can resolve it
            Log.d(TAG,"isServiceOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(ServiceActivity.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "you can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
