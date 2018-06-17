package org.kpdev.eventq_pens.eventq;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.kpdev.eventq_pens.eventq.APIHandler.BaseApiService;
import org.kpdev.eventq_pens.eventq.APIHandler.UtilsApi;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etPassword, etUserName, etName;
    TextView txtViewLogin;
    Button btnRegister;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        initcomponents();
    }

    private void initcomponents() {
        etName = (EditText) findViewById(R.id.editTextName);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etUserName = (EditText) findViewById(R.id.editTextUserName);
        etPassword = (EditText) findViewById(R.id.editTextPassword);
        btnRegister = (Button) findViewById(R.id.buttonRegister);
        txtViewLogin = (TextView) findViewById(R.id.textViewLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menampilkan loading ketika tombol button register di click
                loading = ProgressDialog.show(mContext, null, "Mendaftarkan...", true, false);
                // Melakukan registrasi
                requestRegister();
            }
        });

        txtViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan activity Login saat tombol login diklik
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });
    }

    private void requestRegister() {
        mApiService.registerRequest(etUserName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (!jsonRESULTS.getString("key").equals("")){
                                    Toast.makeText(mContext, "Registrasi Berhasil, Login sekarang!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                } else {
                                    String error_message = jsonRESULTS.getString("non_field_errors");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: TIDAK BERHASIL");
                            loading.dismiss();
                            Toast.makeText(mContext, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
