package br.gov.rodacultural.rodacultural.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;

import br.gov.rodacultural.rodacultural.R;
import br.gov.rodacultural.rodacultural.network.NetworkHelper;
import br.gov.rodacultural.rodacultural.network.ResponseCallback;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilEmail, tilPassword;
    private TextView tvMessage;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        findViewById(R.id.btLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void doLogin() {
        tvMessage.setText("");
        if (isFormValid()) {
            if (!NetworkHelper.isOnline(this)) {
                tvMessage.setText("Você está offline");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            NetworkHelper.getInstance(this).doLogin(
                    tilEmail.getEditText().getText().toString().trim(),
                    tilPassword.getEditText().getText().toString().trim(),
                    new ResponseCallback() {
                        @Override
                        public void onSuccess(String jsonStringResponse, int statusCode) {
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFail(VolleyError error) {
                            progressBar.setVisibility(View.GONE);
                            tvMessage.setText("Falha ao se conectar com o servidor");
                        }
                    });
        }
    }

    private boolean isFormValid() {
        tilEmail.setErrorEnabled(false);
        tilPassword.setErrorEnabled(false);

        String email = tilEmail.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();
        View requestFocus = null;

        if (password.isEmpty() || password.length() < 4) {
            tilPassword.setError("A senha deve conter no mínimo 4 caracteres");
            tilPassword.requestFocus();
            requestFocus = tilPassword;
        }

        if (email.isEmpty()) {
            tilEmail.setError("Insira um e-mail válido");
            tilEmail.requestFocus();
            requestFocus = tilEmail;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("E-mail inválido");
            tilEmail.requestFocus();
            requestFocus = tilEmail;
        }

        return requestFocus == null;
    }
}
