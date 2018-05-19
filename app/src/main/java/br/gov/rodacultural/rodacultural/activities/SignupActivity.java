package br.gov.rodacultural.rodacultural.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import br.gov.rodacultural.rodacultural.R;
import br.gov.rodacultural.rodacultural.network.NetworkHelper;
import br.gov.rodacultural.rodacultural.network.ResponseCallback;
import br.gov.rodacultural.rodacultural.utils.CustomSnackBar;


public class SignupActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextInputLayout tilName, tilBirthday, tilEmail, tilPassword, tilPassword2;

    private HashMap<String, String> data = new HashMap<>();
    private static Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cadastro");

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        tilName = (TextInputLayout) findViewById(R.id.tilName);
        tilBirthday = (TextInputLayout) findViewById(R.id.tilBirthday);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        tilPassword2 = (TextInputLayout) findViewById(R.id.tilPassword2);

        TextInputEditText etBirthday = (TextInputEditText) findViewById(R.id.etBirthday);
        etBirthday.setInputType(InputType.TYPE_NULL);
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        etBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    showDatePickerDialog();
            }
        });

        findViewById(R.id.btSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFormValid()) {
                    progressBar.setVisibility(View.VISIBLE);
                    enableDisableView(findViewById(R.id.parent), false);

                    NetworkHelper.getInstance(SignupActivity.this).signUp(data, new ResponseCallback() {
                        @Override
                        public void onSuccess(String jsonStringResponse, int statusCode) {
                            progressBar.setVisibility(View.GONE);
                            enableDisableView(findViewById(R.id.parent), true);
                            CustomSnackBar.make((ViewGroup) findViewById(R.id.parent), "Usuário cadastrado com sucesso", Snackbar.LENGTH_SHORT, CustomSnackBar.SUCCESS)
                                    .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                                        @Override
                                        public void onDismissed(Snackbar transientBottomBar, int event) {
                                            finish();
                                        }
                                    })
                                    .show();
                        }

                        @Override
                        public void onFail(VolleyError error) {
                            progressBar.setVisibility(View.GONE);
                            enableDisableView(findViewById(R.id.parent), true);
                            CustomSnackBar.make((ViewGroup) findViewById(R.id.parent), "Falha ao cadastrar usuário", Snackbar.LENGTH_LONG, CustomSnackBar.ERROR).show();
                        }
                    });
                }
            }
        });
    }

    private boolean isFormValid() {
        View requestFocusView = null;

        tilPassword.setError(null);
        tilPassword2.setError(null);
        tilEmail.setError(null);
        tilBirthday.setError(null);
        tilName.setError(null);

        if (tilPassword.getEditText().getText().toString().isEmpty()) {
            requestFocusView = tilPassword.getEditText();
            tilPassword.setError("Insira uma senha");
        } else if (tilPassword.getEditText().getText().toString().length() < 6) {
            tilPassword.setError("A senha deve ter no mínimo 6 caracteres");
        } else if (tilPassword2.getEditText().getText().toString().isEmpty()) {
            tilPassword2.setError("Repita a senha");
        } else if (!tilPassword.getEditText().getText().toString().equals(tilPassword2.getEditText().getText().toString())) {
            tilPassword2.setError("As senhas não são iguais");
        } else {
            data.put("password", tilPassword.getEditText().getText().toString());
            data.put("password_confirmation", tilPassword.getEditText().getText().toString());
        }

        if (tilEmail.getEditText().getText().toString().isEmpty()) {
            requestFocusView = tilEmail.getEditText();
            tilEmail.setError("Insira um e-mail válido");
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(tilEmail.getEditText().getText().toString()).matches()) {
            tilEmail.setError("O e-mail inserido não é válido");
        } else {
            data.put("email", tilEmail.getEditText().getText().toString());
        }

        if (tilBirthday.getEditText().getText().toString().isEmpty()) {
            requestFocusView = tilBirthday.getEditText();
            tilBirthday.setError("Insira sua data de nascimento");
        } else {
            try {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                Calendar date = Calendar.getInstance();
                date.setTime(dateFormatter.parse(tilBirthday.getEditText().getText().toString()));
                dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                data.put("birth_date",dateFormatter.format(date.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (tilName.getEditText().getText().toString().isEmpty()) {
            requestFocusView = tilName.getEditText();
            tilName.setError("Insira seu nome completo");
        } else {
            data.put("name",tilName.getEditText().getText().toString());
        }

        if (requestFocusView != null) {
            requestFocusView.requestFocus();
        } else {
            return true;
        }

        return false;
    }

    void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;

            for (int idx = 0; idx < group.getChildCount(); idx++) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
    }

    void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            if (c == null) {
                c = Calendar.getInstance();
            }

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            c.set(year, month, day);
            ((TextInputEditText) getActivity().findViewById(R.id.etBirthday)).setText(dateFormatter.format(c.getTime()));
        }
    }
}
