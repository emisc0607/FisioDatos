package com.emisc0607.activityfisiodatostest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emisc0607.activityfisiodatostest.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private var name: String? = null
    private var password: String? = null
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME_BUNDLE)
            password = it.getString(PASSWORD_BUNDLE)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener {
            if (binding.username.text.isNotEmpty() and binding.password.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.username.text.toString(),
                    binding.password.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            showContent()
                        }
                    else{
                        Toast.makeText(view.context, "Error al crear usuario", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
    }

    private fun showContent() {
        Toast.makeText(view?.context, "Usuario registrado", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val NAME_BUNDLE = "name_bundle"
        const val PASSWORD_BUNDLE = "password_bundle"

        @JvmStatic
        fun newInstance(name: String, password: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME_BUNDLE, name)
                    putString(PASSWORD_BUNDLE, password)
                }
            }
    }
}