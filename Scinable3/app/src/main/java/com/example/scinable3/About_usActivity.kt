package com.example.scinable3;

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about_us.*

class About_usActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        if (savedInstanceState == null) {

            val mainFragment = MainFragment()
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, mainFragment, "main")
                .commit()
        }

        copyButton.setOnClickListener {
            onClick_clipboard()
        }

        mailButton.setOnClickListener {
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

    fun onClick_clipboard() {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("address",getString(R.string.address))
        clipboardManager!!.setPrimaryClip(clipData)
        Toast.makeText(applicationContext, "Copied", Toast.LENGTH_LONG).show()
    }
}
