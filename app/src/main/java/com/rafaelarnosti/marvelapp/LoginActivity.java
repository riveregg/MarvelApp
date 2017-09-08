package com.rafaelarnosti.marvelapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.rafaelarnosti.marvelapp.R;
import com.rafaelarnosti.marvelapp.adapter.MarvelAdapter;
import com.rafaelarnosti.marvelapp.bdResource.bdController;

public class LoginActivity extends AppCompatActivity {
    EditText tilLogin;
    EditText tilSenha;
    LoginButton loginButton;
    CallbackManager callbackManager;
    private SQLiteDatabase db;
    private bdController banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        banco = new bdController(getBaseContext());
        db = banco.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM usuarios", null);

        c.moveToFirst();

        if(c.getCount()!= 0 ){
            tilLogin = (EditText) findViewById(R.id.tilLogin);
            tilSenha = (EditText) findViewById(R.id.tilSenha);
            tilLogin.setText( c.getString(c.getColumnIndex("usuario")));
            tilSenha.setText( c.getString(c.getColumnIndex("senha")));
        }

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent intent = new Intent(LoginActivity.this,
                                MarvelNavigation.class);

                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public void logar(View v){

        String Login = tilLogin.getText().toString();
        String Senha = tilSenha.getText().toString();

        Cursor c = db.rawQuery("SELECT * FROM usuarios WHERE TRIM(usuario) = '" + Login.trim() + "' AND TRIM(senha)= '"+Senha.trim() +"'", null);

        c.moveToFirst();

        if(c.getCount() != 0)
        {
        Intent intent = new Intent(LoginActivity.this,
                MarvelNavigation.class);
            intent.putExtra("usuario",Login);
            intent.putExtra("avatar", c.getInt(c.getColumnIndex("avatar")));
        startActivity(intent);
        LoginActivity.this.finish();
        }
        else{
            Toast.makeText(this.getBaseContext(),"Usuario ou senha Invalidos",Toast.LENGTH_LONG).show();
        }
    }
    public void cadastro (View v){

        CadastroFragment cf = new CadastroFragment();
        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_login,cf)
        .addToBackStack(null)
        .commit();

    }

}
