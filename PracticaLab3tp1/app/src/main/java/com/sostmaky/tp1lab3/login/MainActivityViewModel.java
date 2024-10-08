package com.sostmaky.tp1lab3.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.sostmaky.tp1lab3.model.Usuario;
import com.sostmaky.tp1lab3.request.ApiCliente;

import java.io.Serializable;

import registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> mUsuario;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void mostarFormu() {

        Intent intent = new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //leo los datos de la pantalla de usuario y contraseña

    public void leer(String email,String contrasena ){
        Usuario usuLeido=ApiCliente.login(context,email,contrasena);

        if(usuLeido != null) {
            Intent intent = new Intent(context, RegistroActivity.class);
            intent.putExtra("ingreso",false);
            Bundle bundle = new Bundle();
            bundle.putSerializable("usuario", usuLeido);
            intent.putExtra("datos", bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else{
        Toast.makeText(context,"Usuario no encontrado ",Toast.LENGTH_SHORT).show();

    }

    }




    //leo los datos de la pantalla de usuario y contraseña
   /* public void leerDatos(String email, String contrasena) {
        SharedPreferences sp = context.getSharedPreferences("datos", 0);

        Usuario usuario = null;

        int dni = sp.getInt("dni", -1);
        String apellido = sp.getString("apellido", "-1");
        String nombre = sp.getString("nombre", "-1");
        String mail = sp.getString("email", "-1");
        String contra = sp.getString("contrasena", "-1");
        // Imprimir los datos en la consola
        Log.d("DatosUsuario", "DNI: " + dni);
        Log.d("DatosUsuario", "Apellido: " + apellido);
        Log.d("DatosUsuario", "Nombre: " + nombre);
        Log.d("DatosUsuario", "Email: " + mail);
        Log.d("DatosUsuario", "Contraseña: " + contra);

        if (email.equals(mail) && contrasena.equals(contra)) {
            usuario = new Usuario(dni, apellido, nombre, mail, contra);
            Intent intent = new Intent(context, RegistroActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("usuar", usuario);
            intent.putExtra("usuar",bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else{
            Toast.makeText(context,"Usuario no encontrado ",Toast.LENGTH_SHORT).show();

        }
    }*/
}
