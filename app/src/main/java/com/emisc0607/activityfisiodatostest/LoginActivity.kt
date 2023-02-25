package com.emisc0607.activityfisiodatostest

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.emisc0607.activityfisiodatostest.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings


class LoginActivity : AppCompatActivity() {
    private val GOOGLE_SIGN_IN = 100
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //Permisos
        if (checkPermissions()) {
            //Toast.makeText(this, "Permisos aceptados", Toast.LENGTH_LONG).show()
        } else {
            requestPermissions()
        }
        setup()
        sesion()
        notification()
        //Remote Config
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 5
        }
        val firebaseConfig = Firebase.remoteConfig
        firebaseConfig.setConfigSettingsAsync(configSettings)
        firebaseConfig.setDefaultsAsync(
            mapOf(
                "show_error_button" to false,
                "error_button_text" to "Forzar error",
                "login_password" to "user123"
            )
        )
    }

    private fun notification() {

    }

    override fun onStart() {
        super.onStart()
        binding.auth.visibility = View.VISIBLE
    }

    private fun sesion() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)
        val username = prefs.getString("username", null)
        if (email != null && provider != null && username != null) {
            binding.auth.visibility = View.INVISIBLE
            showMain(email, username, ProviderType.valueOf(provider))
        }
    }

    private fun setup() {
        var remotePassword: String = ""
        title = "Inicio de sesión"
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                remotePassword = Firebase.remoteConfig.getString("login_password")
            }
        }
        binding.bRegister.setOnClickListener {
            if (binding.userEmail.text.isNotEmpty() and binding.remotePassword.text.isNotEmpty() and binding.userPassword.text.isNotEmpty()) {
                if (binding.remotePassword.text.toString() == remotePassword) {

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.userEmail.text.toString(),
                        binding.userPassword.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showMain(
                                it.result?.user?.email ?: "",
                                it.result?.user?.displayName ?: "",
                                ProviderType.BASIC
                            )
                        } else {
                            //Toast.makeText(this, "Error al crear usuario", Toast.LENGTH_SHORT).show()
                            showAlert("Error al generar usuario")
                        }
                    }
                } else {
                    showAlert("Introduzca una contraseña correcta")
                }
            }
        }
        binding.bLogin.setOnClickListener {
            if (binding.userEmail.text.isNotEmpty() and binding.remotePassword.text.isNotEmpty() and binding.userPassword.text.isNotEmpty()) {
                if (binding.remotePassword.text.toString() == remotePassword) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        binding.userEmail.text.toString(),
                        binding.userPassword.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showMain(
                                it.result?.user?.email ?: "",
                                it.result?.user?.email ?: "",
                                ProviderType.BASIC
                            )
                        } else {
                            //Toast.makeText(this, "Error al crear usuario", Toast.LENGTH_SHORT).show()
                            showAlert("Introduzca usuario o contraseña correctos ")
                        }
                    }
                } else {
                    showAlert("Introduzca una contraseña correcta")
                }
            }
        }
        binding.googleButton.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    if (binding.remotePassword.text.toString() == Firebase.remoteConfig.getString("login_password")) {
                        FirebaseAuth.getInstance().signInWithCredential(credential)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    showMain(
                                        account.email ?: "",
                                        account.givenName ?: "",
                                        ProviderType.GOOGLE
                                    )
                                } else {
                                    showAlert("Error al generar usuario")
                                }
                            }
                    } else {
                        showAlert("Introduzca contraseña correcta")
                    }
                }
            } catch (e: ApiException) {
                showAlert("Error al generar usuario")
            }
        }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error").setMessage(message)
            .setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showMain(email: String, username: String, provider: ProviderType) {
        val homeIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("username", username)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

    private fun checkPermissions(): Boolean {
        val per1 =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val per2 =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val per3 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        return per1 == PackageManager.PERMISSION_GRANTED
                && per2 == PackageManager.PERMISSION_GRANTED
                && per3 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ), 200
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 200) {
            if (grantResults.isNotEmpty()) {
                val writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED
                val cameraUse = grantResults[2] == PackageManager.PERMISSION_GRANTED
                if (writeStorage && readStorage && cameraUse) {
                } else {
                    Toast.makeText(this, "Permisos denegados", Toast.LENGTH_LONG).show()
                    //finish()
                }
            }

        }
    }
}