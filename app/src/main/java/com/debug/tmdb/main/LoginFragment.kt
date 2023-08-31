package com.debug.tmdb.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.debug.tmdb.BuildConfig
import com.debug.tmdb.R
import com.debug.tmdb.main.remoto.ServiceProvider

class LoginFragment : Fragment() {

    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        navController?.let { nav ->
            view.findViewById<AppCompatButton>(R.id.login_btn).setOnClickListener {
                nav.navigate(R.id.action_loginFragment_to_homeFragment)
            }
            view.findViewById<TextView>(R.id.login_tv_register).setOnClickListener {
                nav.navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }
}