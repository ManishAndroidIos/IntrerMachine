package com.app.itauditor.views.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.app.itauditor.R;
import com.app.itauditor.retrofit.RequestNotifier;
import com.app.itauditor.retrofit.app_ApiCall;
import com.app.itauditor.utills.AppAlert;
import com.app.itauditor.utills.ProgressView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Response;

import static com.app.itauditor.utills.chk_String_Operation.isStringNullOrEmpty;
import static com.app.itauditor.utills.chk_String_Operation.isValidEmail;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener, RequestNotifier {


    private LinearLayout mMainLay;
    private LinearLayout mLayName;
    private EditText mEtName;
    private LinearLayout mLayMob;
    private EditText mEtMobile;
    private LinearLayout mLayEmail;
    private EditText mEtEmail;
    private LinearLayout mLayPassword;
    private EditText mEtPassword;
    private Button mBtnSignUp;
    private LinearLayout mLayNewUser;
    private LinearLayout mLayConfPassword;
    private EditText mEtConfPassword;

    Fragment mFragment;

    private app_ApiCall mapp_apiCall;

    private String UserName, MobileNumber ="", EmailId, Password,ConfPassword;


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initView(view);
        mapp_apiCall = new app_ApiCall(getActivity(), this);

        return view;
    }

    private void initView(View view) {
        mMainLay = view.findViewById(R.id.main_lay);
        mLayName = view.findViewById(R.id.lay_name);
        mEtName = view.findViewById(R.id.et_name);
        mLayMob = view.findViewById(R.id.lay_mob);
        mEtMobile = view.findViewById(R.id.et_mobile);
        mLayEmail = view.findViewById(R.id.lay_email);
        mEtEmail = view.findViewById(R.id.et_email);
        mLayPassword = view.findViewById(R.id.lay_password);
        mEtPassword = view.findViewById(R.id.et_password);
        mBtnSignUp = view.findViewById(R.id.btn_SignUp);
        mLayNewUser = view.findViewById(R.id.lay_new_user);

        mLayConfPassword = view.findViewById(R.id.lay_conf_password);
        mEtConfPassword = view.findViewById(R.id.et_conf_password);


        mBtnSignUp.setOnClickListener(this);
        mLayNewUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_SignUp:

                UserName = mEtName.getText().toString();
                MobileNumber = mEtMobile.getText().toString();
                EmailId = mEtEmail.getText().toString();
                Password = mEtPassword.getText().toString();
                ConfPassword = mEtConfPassword.getText().toString();

                isAllValid(UserName, MobileNumber, EmailId, Password,ConfPassword);
                break;

            case R.id.lay_new_user:
                callFragment();
                break;

        }
    }

    private void isAllValid(String userName, String mobileNumber, String emailId, String password, String confPassword) {

        if (isStringNullOrEmpty(userName)) {
            AppAlert.callAlertDialog(getContext(), "Error", "Please Enter UserName !",
                    3);
        }else if (userName.matches(".*\\s+.*")) {
            AppAlert.callAlertDialog(getContext(), "Error", "Please Enter Valid UserName !",
                    3);
        }/* else if (isStringNullOrEmpty(mobileNumber)) {
            AppAlert.callAlertDialog(getContext(), "Error", "Please Enter Mobile Number !",
                    3);
        } else if (mobileNumber.length() < 10) {
            AppAlert.callAlertDialog(getContext(), "Alert", "Please Enter Valid Mobile Number !",
                    3);
        } */else if (isStringNullOrEmpty(emailId)) {
            AppAlert.callAlertDialog(getContext(), "Error", "Please Enter Email ID !",
                    3);
        } else if (isValidEmail(emailId)) {
            AppAlert.callAlertDialog(getContext(), "Error", "Please Enter Valid Email ID !",
                    3);
        } else if (isStringNullOrEmpty(password)) {
            AppAlert.callAlertDialog(getContext(), "Error", "Please Enter Password !",
                    3);
        } else if (isStringNullOrEmpty(confPassword)) {
            AppAlert.callAlertDialog(getContext(), "Error", "Please Enter Confirm Password !",
                    3);
        } else if (!password.equals(confPassword)) {
            AppAlert.callAlertDialog(getContext(), "Error", "Password Dose not Match !",
                    3);
        } else {
            RegisterUser(UserName, MobileNumber, EmailId, Password);
        }

    }
    private void RegisterUser(String userName, String mobileNumber, String emailId, String password) {
        ProgressView.show(getContext());
        mapp_apiCall.userRegistration(0,userName, emailId, password);
    }



    @Override
    public void notifySuccess(Response<?> response, int code) {
        ProgressView.dismiss();

        if (code == 201){
            callAlertDialog(getActivity(), "Success", "User Registered Successfully !",
                    2);
        }else if (code == 409){
            AppAlert.callAlertDialog(getActivity(), "Error", "User already exists. Please Log in. !",
                    3);
        }else {
            AppAlert.callAlertDialog(getActivity(), "Error", "Internal Server Error !",
                    3);
        }

    }

    @Override
    public void notifyError(Throwable error) {

        ProgressView.dismiss();
        System.out.println("ererererere " + error);
    }

    @Override
    public void notifyString(String str) {
        ProgressView.dismiss();
        System.out.println("dvdvduvdyuvyudv " + str);
    }


    private void callFragment() {
        mFragment = new LoginFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, mFragment, "LoginFragment")
                .commit();
    }


    public void callAlertDialog(Context context, String title, String message, int Alert_Type){
        /*SweetAlertDialog WARNING_TYPE = new SweetAlertDialog(context, Alert_Type);
        WARNING_TYPE.setCancelable(false);
        WARNING_TYPE.setTitleText(title)
                .setContentText(message)
                .setConfirmText("Ok")
                .showCancelButton(false)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        callFragment();
                        sDialog.cancel();
                    }
                })
                .show();

        WARNING_TYPE.getButton(SweetAlertDialog.BUTTON_CONFIRM)
                .setTextColor(context.getResources().getColor(R.color.snow));

        WARNING_TYPE.getButton(SweetAlertDialog.BUTTON_CONFIRM)
                .setBackground(context.getDrawable(R.drawable.btn_back));*/

        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context, R.style.RoundShapeTheme);

        materialAlertDialogBuilder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callFragment();
                        dialogInterface.dismiss();
                    }
                })
                /*.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })*/
                .show();
    }
}
