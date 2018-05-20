package dreamso.smart_worker;

import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dreamso.smart_worker.R;
import dreamso.smart_worker.models.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class SignUp_Fragment extends Fragment implements OnClickListener {
	private static View view;
	private static EditText fullName, emailId, mobileNumber, nic,
			password, confirmPassword;
	private static TextView login;
	private static Button signUpButton;
	private static CheckBox terms_conditions;

	private ProgressDialog mProgress;
	//defining firebase object
	private FirebaseAuth mAuth;
	//defining db
	private DatabaseReference mDatabase;

	private DatabaseReference mDatabaseEmail;

	private DatabaseReference mDatabaseindex;

	public SignUp_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.signup_layout, container, false);
		initViews();
		setListeners();

		mProgress = new ProgressDialog(getActivity());
		// Initializing Firebase object
		mAuth = FirebaseAuth.getInstance();



		//get the firebase database reference
		mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
		mDatabaseindex = FirebaseDatabase.getInstance().getReference().child("UserIndex");
		mDatabaseEmail = FirebaseDatabase.getInstance().getReference().child("UniqueID");

		return view;
	}

	// Initialize all views
	private void initViews() {
		fullName = (EditText) view.findViewById(R.id.fullName);
		emailId = (EditText) view.findViewById(R.id.userEmailId);
		mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
		nic = (EditText) view.findViewById(R.id.nicNumber);
		password = (EditText) view.findViewById(R.id.password);
		confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
		signUpButton = (Button) view.findViewById(R.id.signUpBtn);
		login = (TextView) view.findViewById(R.id.already_user);
		terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);

		// Setting text selector over textviews
		XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

			login.setTextColor(csl);
			terms_conditions.setTextColor(csl);
		} catch (Exception e) {
		}
	}

	// Set Listeners
	private void setListeners() {
		signUpButton.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.signUpBtn:

				// register user
				registerUser();
				break;

			case R.id.already_user:

				// Replace login fragment
				new MainActivity().replaceLoginFragment();
				break;
		}

	}

	// Check Validation Method
	private int checkValidation() {

		int flag = 1;
		// Get all edittext texts
		String getFullName = fullName.getText().toString();
		String getEmailId = emailId.getText().toString().trim();
		String getMobileNumber = mobileNumber.getText().toString().trim();
		String getnic = nic.getText().toString().trim();
		String getPassword = password.getText().toString().trim();
		String getConfirmPassword = confirmPassword.getText().toString().trim();

		// Pattern match for email id
		Pattern p = Pattern.compile(Utils.regEx);
		Matcher m = p.matcher(getEmailId);

		// Check if all strings are null or not
		if (getFullName.equals("") || getFullName.length() == 0
				|| getEmailId.equals("") || getEmailId.length() == 0
				|| getMobileNumber.equals("") || getMobileNumber.length() == 0
				|| getnic.equals("") || getnic.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0
				|| getConfirmPassword.equals("")
				|| getConfirmPassword.length() == 0){
			flag = 0;
			new CustomToast().Show_Toast(getActivity(), view,
					"All fields are required.");
		}

		// Check if email id valid or not
		else if (!m.find()){
			flag = 0;
			new CustomToast().Show_Toast(getActivity(), view,
					"Your Email Id is Invalid.");}

		else if (getPassword.length() <6){
			flag = 0;
			new CustomToast().Show_Toast(getActivity(), view,
					"password should be at least 6 characters.");}

		// Check if both password should be equal
		else if (!getConfirmPassword.equals(getPassword)){
			flag = 0;
			new CustomToast().Show_Toast(getActivity(), view,
					"Both password doesn't match.");}

		// Make sure user should check Terms and Conditions checkbox
		else if (!terms_conditions.isChecked()){
			flag = 0;
			new CustomToast().Show_Toast(getActivity(), view,
					"Please select Terms and Conditions.");}

		// Else do signup or do your stuff
		else{
			Toast.makeText(getActivity(), "Signing up....", Toast.LENGTH_SHORT)
					.show();}

		return flag;

	}

	public void registerUser(){

		if(checkValidation() == 1){

			String name = fullName.getText().toString().trim();
			final String email = emailId.getText().toString().trim();
			final String mobile = mobileNumber.getText().toString().trim();
			final String nicNo = nic.getText().toString().trim();
			String pwd = password.getText().toString().trim();

			final User newUser = new User(name,email,mobile,nicNo,pwd);


			//if validations are ok a progress bar will be shown
			//mProgress.setMessage("Registering User...");
			//mProgress.show();

			//creating a new user
			mAuth.createUserWithEmailAndPassword(newUser.getEmail(),newUser.getPassword())
					.addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
						@Override
						public void onComplete(@NonNull Task<AuthResult> task) {
							if (task.isSuccessful()){
								//user is successfully registered and logged in


								FirebaseUser user = mAuth.getCurrentUser();
								DatabaseReference currnt_userDB = mDatabase.child(user.getUid());
								currnt_userDB.child("name").setValue(newUser.getName());
								currnt_userDB.child("email").setValue(newUser.getEmail());
								currnt_userDB.child("mobile").setValue(newUser.getPhoneNumber());
								currnt_userDB.child("NIC").setValue(newUser.getNicid());
								currnt_userDB.child("image").setValue("default");

								String emailID = Integer.toString((int)System.currentTimeMillis());
								//mDatabaseEmail = FirebaseDatabase.getInstance().getReference().child("UniqueID");
								mDatabaseEmail.child(emailID).setValue(user.getEmail());


								String userId = mAuth.getCurrentUser().getUid();

								mDatabaseindex.child(newUser.getNicid()).setValue(userId);

								getActivity().finish();
								new MainActivity().replaceLoginFragment();



							}
							else
							{

								new CustomToast().Show_Toast(getActivity(), view,
										"Registration failed.");
							}





						}
					});

		}


	}
}
