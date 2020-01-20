package com.example.scinable3

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.push_lib.FCMRequest
import com.example.push_lib.PushStorage
import com.example.push_lib.Scinable
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    private var isKnowledge = true;
    private var isArchitecture = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uus = FCMRequest(applicationContext.packageName, this::class.java.simpleName, R.drawable.caulogo2, this)
        val pushIntent = intent
        setContentView(R.layout.activity_main)

        val sds = Scinable(applicationContext, pushIntent.extras)
        sds.push("setAccountId", "scinable")
        sds.push("setHost", "test.scinable.net")
        sds.push("setLanguage", "ko")
        sds.push(
            "addMember",
            "M1",
            "address",
            "남",
            "19941030",
            "99",
            "scinable.com",
            "kang",
            "seokmin",
            "1",
            "{1: '100', 2: '200'}"
        )
        sds.push("setConversion",
            "2",
            "3000",
            "{1: '100,200', 2: '500', 5: '220'}"
        )

        sds.push("trackView")

        resetData(null, null, null, null, null, null)

        val knowledge = findViewById<LinearLayout>(R.id.knowledge)
        val architecture = findViewById<LinearLayout>(R.id.architecture)

        architecture.visibility = View.GONE
        knowledge.visibility = View.GONE

        solutionButton.setOnClickListener {
            val solutionIntent = Intent(this, SolutionActivity::class.java)
            startActivity(solutionIntent)
        }

        ourServiceButton.setOnClickListener{
            val serviceIntent = Intent(this, ServiceActivity::class.java)
            startActivity(serviceIntent)
        }

        aboutUsButton.setOnClickListener {
            val aboutUsIntent = Intent(this, About_usActivity::class.java)
            startActivity(aboutUsIntent)
        }

        knowledgeButton.setOnClickListener {

            if(isKnowledge){
                architecture.visibility = View.GONE
                knowledge.visibility = View.VISIBLE
            }
            else{
                knowledge.visibility = View.GONE
            }
            isKnowledge = !isKnowledge
        }

        architectureButton.setOnClickListener {
            if(isArchitecture) {
                architecture.visibility = View.VISIBLE
                knowledge.visibility = View.GONE
            }
            else{
                architecture.visibility = View.GONE
            }
            isArchitecture = !isArchitecture
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
        when(item?.itemId){
            R.id.action_solution -> {
                val solutionIntent = Intent(this, SolutionActivity::class.java)
                startActivity(solutionIntent)
            }
            R.id.action_service ->{
                val serviceIntent = Intent(this, ServiceActivity::class.java)
                startActivity(serviceIntent)
            }
            R.id.action_about_us -> {
                val aboutUsIntent = Intent(this, About_usActivity::class.java)
                startActivity(aboutUsIntent)
            }
            R.id.action_home -> {
                val mainIntent = Intent(this, PushStorage::class.java)
                startActivity(mainIntent)
            }
            R.id.action_mail ->{
                val mailIntent = Intent(this, MailActivity::class.java)
                startActivity(mailIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun resetData(company: String?, department: String?, name: String?, mail: String?, phone: String?, ask: String?){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putString("company", company)
            .putString("department", department)
            .putString("name",name)
            .putString("mail",mail)
            .putString("phone",phone)
            .putString("ask",ask)
            .apply()
    }
}
