package com.example.scinable3

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_mail.*
import org.json.JSONObject
import android.widget.Spinner as Spinner

class MailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mail)

        val spinner = findViewById<Spinner>(R.id.mailSpinner)
        val adapter = ArrayAdapter.createFromResource(this, R.array.mail, android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)
        loadData()

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build()
        )

        hereButton.setOnClickListener {
            saveData(
                company.text.toString(),
                department.text.toString(),
                name.text.toString(),
                mail.text.toString(),
                phone.text.toString(),
                ask.text.toString()
            )

            val checkIntent = Intent(this, CheckActivity::class.java)
            startActivity(checkIntent)
        }
        sendButton.setOnClickListener {

            val companytext = company.text.toString()
            val departmentText = department.text.toString()
            val nametext = name.text.toString()
            val mailText = mail.text.toString()
            val addresstext = spinner.selectedItem.toString()
            val phonetext = phone.text.toString()
            val askText = ask.text.toString()

            var dialog = AlertDialog.Builder(this)
            val gms = Gmailsender("20166439.sw.cau@gmail.com", "trilogy!0628")

            if (companytext.equals("") || nametext.equals("") || mailText.equals("")) {
                dialog.setMessage("*印は必須項目です。")
                    .setNegativeButton("check", null)
                    .create()
                dialog.show()
                return@setOnClickListener
            } else if (addresstext.equals("選択しない")) {
                dialog.setMessage("メールアドレスを選択してください")
                    .setNegativeButton("check", null)
                    .create()
                dialog.show()
                return@setOnClickListener
            }

            val responseListener = Response.Listener<String> { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")

                    if (success) {
                        gms.sendMail(
                            companytext,
                            departmentText,
                            nametext,
                            mailText + "@" + addresstext,
                            phonetext,
                            askText,
                            "20166439.sw.cau@gmail.com"
                        )
                        Toast.makeText(this@MailActivity, "転送完了", Toast.LENGTH_SHORT).show();
                        val intent = Intent(this@MailActivity, MainActivity::class.java)
                        this@MailActivity.startActivity(intent)
                        finish()
                    }
                    else {
                        dialog.setMessage("送信に失敗し.")
                            .setNegativeButton("再試行", null)
                            .create()
                        dialog.show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            val storeRequest = StoreRequest(
                companytext,
                departmentText,
                nametext,
                mailText + "@" + addresstext,
                phonetext,
                askText,
                responseListener
            )
            val queue = Volley.newRequestQueue(this@MailActivity)
            queue.add(storeRequest)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mail, menu)
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
            R.id.action_back -> {
                resetData(null, null, null, null, null, null)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveData(
        company: String?,
        department: String?,
        name: String?,
        mail: String?,
        phone: String?,
        ask: String?
    ) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putString("company", company)
            .putString("department", department)
            .putString("name", name)
            .putString("mail", mail)
            .putString("phone", phone)
            .putString("ask", ask)
            .apply()
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val companytext = pref.getString("company", null)
        val departmenttext = pref.getString("department", null)
        val nametext = pref.getString("name", null)
        val mailtext = pref.getString("mail", null)
        val phonetext = pref.getString("phone", null)
        val asktext = pref.getString("ask", null)

        if (companytext != null) company.setText(companytext.toString())

        if (departmenttext != null) department.setText(departmenttext.toString())

        if (nametext != null) name.setText(nametext.toString())

        if (mailtext != null) mail.setText(mailtext.toString())

        if (phonetext != null) phone.setText(phonetext.toString())

        if (asktext != null) ask.setText(asktext.toString())

    }

    private fun resetData(
        company: String?,
        department: String?,
        name: String?,
        mail: String?,
        phone: String?,
        ask: String?
    ) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putString("company", company)
            .putString("department", department)
            .putString("name", name)
            .putString("mail", mail)
            .putString("phone", phone)
            .putString("ask", ask)
            .apply()
    }
}
