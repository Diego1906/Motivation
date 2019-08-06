package com.example.motivation.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.example.motivation.R
import com.example.motivation.util.MotivationConstants
import com.example.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)

        setListeners()

        verifyUserName()
    }

    private fun setListeners() {
        buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val id = view?.id
        if (id == R.id.buttonSave)
            handleSave()
    }

    private fun handleSave() {
        val name: String = editName.text.toString()

        if (name.isEmpty()) {
            Snackbar.make(constraintSplash, getString(R.string.informe_seu_nome), Snackbar.LENGTH_LONG).show()
        } else {
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)

            startActivity()
        }
    }

    private fun verifyUserName() {
        val userName = mSecurityPreferences.getStoreString(MotivationConstants.KEY.PERSON_NAME)
        if (!userName.isNullOrEmpty()) {
            startActivity()
        }
        editName.setText(userName)
    }

    private fun startActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
