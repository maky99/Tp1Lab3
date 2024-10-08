package registro;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.sostmaky.tp1lab3.R;
import com.sostmaky.tp1lab3.databinding.ActivityRegistroBinding;
import com.sostmaky.tp1lab3.login.MainActivityViewModel;
import com.sostmaky.tp1lab3.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel viewModel;
    private ActivityRegistroBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        binding=ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel.getMUsuario().observe(this, new Observer<Usuario>() {
    @Override
    public void onChanged(Usuario usuario) {

        binding.tvDni.setText(usuario.getDni()+"");
        binding.tvApellido.setText(usuario.getApellido());
        binding.tvNombre.setText(usuario.getNombre());
        binding.tvEmail.setText(usuario.getMail());
        binding.tvContrasena.setText(usuario.getContrasena());

    }
});


        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.guardarUsuario(binding.tvDni.getText().toString(),binding.tvApellido.getText().toString(),binding.tvNombre.getText().toString(),binding.tvEmail.getText().toString(),binding.tvContrasena.getText().toString());

            }
        });
        viewModel.leerusuario(getIntent());
    }
}
