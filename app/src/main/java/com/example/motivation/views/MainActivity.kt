package com.example.motivation.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.motivation.R
import com.example.motivation.util.MotivationConstants
import com.example.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mFilter = MotivationConstants.PHRASE_FILTER.ALL

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mSecurityPreferences = SecurityPreferences(this)

        // Eventos
        setListeners()

        // Inicializa
        handleFilter(R.id.imageAll)

        setUserName()
    }

    override fun onClick(view: View?) {
        val id = view?.id
        when (id) {
            R.id.imageAll, R.id.imageSun, R.id.imageHappy -> {
                handleFilter(id)
            }
            else -> {
                refreshPhrase()
            }
        }
    }

    private fun setUserName() {
        textUserName.text = "Olá, ${mSecurityPreferences.getStoreString(MotivationConstants.KEY.PERSON_NAME)}"
    }

//    private fun setPhrase() {
//        textPhrase.text =
//    }

    private fun setListeners() {
        imageAll.setOnClickListener(this)
        imageSun.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        buttonNewPhrase.setOnClickListener(this)
    }

    private fun handleFilter(id: Int) {
        refreshImage(imageAll, R.drawable.ic_all_unselected)
        refreshImage(imageSun, R.drawable.ic_sun_unselected)
        refreshImage(imageHappy, R.drawable.ic_happy_unselected)

        when (id) {
            R.id.imageAll -> {
                mFilter = MotivationConstants.PHRASE_FILTER.ALL
                refreshImage(imageAll, R.drawable.ic_all_selected)
            }
            R.id.imageSun -> {
                mFilter = MotivationConstants.PHRASE_FILTER.SUN
                refreshImage(imageSun, R.drawable.ic_sun_selected)
            }
            R.id.imageHappy -> {
                mFilter = MotivationConstants.PHRASE_FILTER.HAPPY
                refreshImage(imageHappy, R.drawable.ic_happy_selected)
            }
        }
    }

    private fun refreshImage(imageView: ImageView, resourceId: Int) {
        imageView.setImageResource(resourceId)
    }

    private fun refreshPhrase() {}
}
