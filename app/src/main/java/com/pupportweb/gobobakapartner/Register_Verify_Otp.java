package com.pupportweb.gobobakapartner;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pupportweb.gobobakapartner.widgit.OTPEditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Register_Verify_Otp extends AppCompatActivity {
    private String mVerificationId;

    //The edittext to input the code
//    private EditText otpeditext;
    Button otpverify,bBack;
    String mobile;
    String otpCode;
    TextView otpresend;
    //firebase auth object
    private FirebaseAuth mAuth;

    private OTPEditText etOne;
    private OTPEditText etTwo;
    private OTPEditText etThree;
    private OTPEditText etFour;
    private OTPEditText etFive;
    private OTPEditText etSix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__verify__otp);
        setId();
        setOnClickListner();

        //initializing objects
        mAuth = FirebaseAuth.getInstance();

        //getting mobile number from the previous activity
        //and sending the verification code to the number
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        sendVerificationCode(mobile);
    }

    public void setId() {
//        otpeditext = findViewById(R.id.otpedit);
        otpverify = findViewById(R.id.b_verifyotp);
        otpresend = findViewById(R.id.btn_re_send_otp);

        etOne=findViewById(R.id.et_registration_mobile_one);
        etTwo=findViewById(R.id.et_registration_mobile_two);
        etThree=findViewById(R.id.et_registration_mobile_three);
        etFour=findViewById(R.id.et_registration_mobile_four);
        etFive=findViewById(R.id.et_registration_mobile_five);
        etSix=findViewById(R.id.et_registration_mobile_six);

        etOne.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Integer textlength1 = etOne.getText().length();

                if (textlength1 >= 1) {
                    etOne.setBackgroundResource(R.drawable.edit_text_otp);
                    etTwo.requestFocus();
                } else {
                    etOne.setBackgroundResource(R.drawable.edit_text_otp);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
        });

        etTwo.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Integer textlength2 = etTwo.getText().length();

                if (textlength2 >= 1) {
                    etTwo.setBackgroundResource(R.drawable.edit_text_otp);
                    etThree.requestFocus();
                } else {
                    etTwo.setBackgroundResource(R.drawable.edit_text_otp);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }
        });
        etThree.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Integer textlength3 = etThree.getText().length();

                if (textlength3 >= 1) {
                    etThree.setBackgroundResource(R.drawable.edit_text_otp);
                    etFour.requestFocus();
                } else {
                    etThree.setBackgroundResource(R.drawable.edit_text_otp);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }
        });
        etFour.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Integer textlength4 = etFour.getText().toString().length();

                if (textlength4 == 1) {
                    etFour.setBackgroundResource(R.drawable.edit_text_otp);
                    etFive.requestFocus();
                } else {
                    etFour.setBackgroundResource(R.drawable.edit_text_otp);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });
        etFive.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Integer textlength4 = etFive.getText().toString().length();

                if (textlength4 == 1) {
                    etFive.setBackgroundResource(R.drawable.edit_text_otp);
                    etSix.requestFocus();
                } else {
                    etFive.setBackgroundResource(R.drawable.edit_text_otp);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });
        etSix.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Integer textlength4 = etSix.getText().toString().length();

                if (textlength4 == 1) {
                    etSix.setBackgroundResource(R.drawable.edit_text_otp);
                } else {
                    etSix.setBackgroundResource(R.drawable.edit_text_otp);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });
        etSix.setOnDeleteKeyClick(new OTPEditText.OnDeleteKeyClick() {
            @Override
            public void onDeleteKeyClick(boolean isPressed) {

                int i = etSix.getText().toString().length();
                if (i == 0) {
                    etFive.setText("");
                    etFive.requestFocus();
                }
            }
        });
        etFive.setOnDeleteKeyClick(new OTPEditText.OnDeleteKeyClick() {
            @Override
            public void onDeleteKeyClick(boolean isPressed) {

                int i = etFive.getText().toString().length();
                if (i == 0) {
                    etFour.setText("");
                    etFour.requestFocus();
                }
            }
        });
        etFour.setOnDeleteKeyClick(new OTPEditText.OnDeleteKeyClick() {
            @Override
            public void onDeleteKeyClick(boolean isPressed) {

                int i = etFour.getText().toString().length();
                if (i == 0) {
                    etThree.setText("");
                    etThree.requestFocus();
                }
            }
        });

        etThree.setOnDeleteKeyClick(new OTPEditText.OnDeleteKeyClick() {
            @Override
            public void onDeleteKeyClick(boolean isPressed) {

                int i = etThree.getText().toString().length();
                if (i == 0) {
                    etTwo.setText("");
                    etTwo.requestFocus();
                }
            }
        });

        etTwo.setOnDeleteKeyClick(new OTPEditText.OnDeleteKeyClick() {
            @Override
            public void onDeleteKeyClick(boolean isPressed) {

                int i = etTwo.getText().toString().length();
                if (i == 0) {
                    etOne.setText("");
                    etOne.requestFocus();
                }
            }
        });

        etSix.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    if (etOne.getText().toString().length() == 0) {
                        etOne.requestFocus();
                    } else if (etTwo.getText().toString().length() == 0) {
                        etTwo.requestFocus();
                    } else if (etThree.getText().toString().length() == 0) {
                        etThree.requestFocus();
                    } else if (etFour.getText().toString().length() == 0) {
                        etFour.requestFocus();
                    } else if (etFour.getText().toString().length() == 0) {
                        etFive.requestFocus();
                    }
                }
            }
        });

        etFive.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    if (etOne.getText().toString().length() == 0) {
                        etOne.requestFocus();
                    } else if (etTwo.getText().toString().length() == 0) {
                        etTwo.requestFocus();
                    } else if (etThree.getText().toString().length() == 0) {
                        etThree.requestFocus();
                    } else if (etFour.getText().toString().length() == 0) {
                        etFour.requestFocus();
                    }
                }
            }
        });

        etFour.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    if (etOne.getText().toString().length() == 0) {
                        etOne.requestFocus();
                    } else if (etTwo.getText().toString().length() == 0) {
                        etTwo.requestFocus();
                    } else if (etThree.getText().toString().length() == 0) {
                        etThree.requestFocus();
                    }
                }
            }
        });

        etThree.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    if (etOne.getText().toString().length() == 0) {
                        etOne.requestFocus();
                    } else if (etTwo.getText().toString().length() == 0) {
                        etTwo.requestFocus();
                    }

                }
            }
        });

        etTwo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    if (etOne.getText().toString().length() == 0) {
                        etOne.requestFocus();
                    }
                }
            }
        });

    }

    public void setOnClickListner() {

        otpverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpCode = "" + etOne.getText().toString() + etTwo.getText().toString()
                        + etThree.getText().toString() + etFour.getText().toString()
                        + etFive.getText().toString() + etSix.getText().toString();
                String code = otpCode.toString().trim();
//                String code = otpeditext.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
//                    otpeditext.setError("Enter valid code");
//                    otpeditext.requestFocus();
                    Toast.makeText(Register_Verify_Otp.this,"Enter valid code",Toast.LENGTH_LONG).show();
                    return;
                }
                else {

                    Intent intent = new Intent(Register_Verify_Otp.this, Register_screen2.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("mobile", mobile);
                    startActivity(intent);
//                    verifyVerificationCode(code);
                }
                //verifying the code entered manually
//                verifyVerificationCode(code);
            }
        });

        otpresend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String mobile = intent.getStringExtra("mobile");
//                Log.d(TAG, "onClick:Resent "+mobile);
                sendVerificationCode(mobile);
            }
        });
    }


    //the method is sending verification code
    //the country id is concatenated
    //you can take the country id as user input as well
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }


    //the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//
//            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                otpCode = "" + etOne.getText().toString() + etTwo.getText().toString()
                        + etThree.getText().toString() + etFour.getText().toString()
                        + etFive.getText().toString() + etSix.getText().toString();
                //verifying the code
                verifyVerificationCode(otpCode);
/*           }
//            if (otpeditext.equals("") || otpeditext.length() < 6) {
//                otpeditext.setError("Enter Valid Code");
//                otpeditext.requestFocus();
//                return;
//            }else
//
//            {
//                verifyVerificationCode(otpeditext.getText().toString());
*/
            }


        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Register_Verify_Otp.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };


    private void verifyVerificationCode(String code) {
        //creating the credential
        if (!code.equalsIgnoreCase("")) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

            //signing the user

            signInWithPhoneAuthCredential(credential);
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Register_Verify_Otp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register_Verify_Otp.this, "Mobile No Verified Successfully", Toast.LENGTH_SHORT).show();

                            //verification successful we will start the profile activity

                            Intent intent = new Intent(Register_Verify_Otp.this, Register_screen2.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("mobile", mobile);
                            startActivity(intent);

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                            Toast.makeText(Register_Verify_Otp.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Register_Verify_Otp.this, RegisterActivity.class);
        startActivity(i);
        finish();    }
}
