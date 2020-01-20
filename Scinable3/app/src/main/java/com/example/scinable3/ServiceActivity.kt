package com.example.scinable3

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {

    private var isLayout1 = true
    private var isLayout2 = true
    private var isLayout3 = true
    private var isLayout4 = true
    private var isLayout5 = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        val layout1 = findViewById<LinearLayout>(R.id.layout1)
        val layout2 = findViewById<LinearLayout>(R.id.layout2)
        val layout3 = findViewById<LinearLayout>(R.id.layout3)
        val layout4 = findViewById<LinearLayout>(R.id.layout4)
        val layout5 = findViewById<LinearLayout>(R.id.layout5)

        layout1.visibility = View.GONE
        layout2.visibility = View.GONE
        layout3.visibility = View.GONE
        layout4.visibility = View.GONE
        layout5.visibility = View.GONE

        layout1button.setOnClickListener {
            if(isLayout1) {
                layout1.visibility = View.VISIBLE
            }
            else  {
                layout1.visibility = View.GONE
            }
            isLayout1 = !isLayout1
        }

        layout2button.setOnClickListener {
            if(isLayout2) {
                layout2.visibility = View.VISIBLE
            }
            else  {
                layout2.visibility = View.GONE
            }
            isLayout2 = !isLayout2
        }

        layout3button.setOnClickListener {
            if(isLayout3) {
                layout3.visibility = View.VISIBLE
            }
            else  {
                layout3.visibility = View.GONE
            }
            isLayout3 = !isLayout3
        }

        layout4button.setOnClickListener {
            if(isLayout4) {
                layout4.visibility = View.VISIBLE
            }
            else  {
                layout4.visibility = View.GONE
            }
            isLayout4 = !isLayout4
        }

        layout5button.setOnClickListener {
            if(isLayout5) {
                layout5.visibility = View.VISIBLE
            }
            else  {
                layout5.visibility = View.GONE
            }
            isLayout5 = !isLayout5
        }

        mailButton.setOnClickListener{
            val mailIntent = Intent(this, MailActivity::class.java)
            startActivity(mailIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_solution -> {
                val solutionIntent = Intent(this, SolutionActivity::class.java)
                startActivity(solutionIntent)
            }
            R.id.action_service -> {
                val serviceIntent = Intent(this, ServiceActivity::class.java)
                startActivity(serviceIntent)
            }
            R.id.action_about_us -> {
                val aboutUsIntent = Intent(this, About_usActivity::class.java)
                startActivity(aboutUsIntent)
            }
            R.id.action_home -> {
                val mainIntent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
            }
            R.id.action_mail -> {
                val mailIntent = Intent(this, MailActivity::class.java)
                startActivity(mailIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
