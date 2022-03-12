package com.example.newsproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun openTechNews(view: View) {
        var i = Intent(this, MainActivity::class.java)
        i.putExtra("cat", "technology")
        startActivity(i)
    }
    fun openSportNews(view: View) {
        var i = Intent(this, MainActivity::class.java)
        i.putExtra("cat", "sports")
        startActivity(i)
    }

    override fun onBackPressed() {
        val exitDialog = ExitDialog()
        exitDialog.isCancelable = false
        exitDialog.show(supportFragmentManager, null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val i = Intent(this, SettingsActivity::class.java)
        startActivity(i)
        return super.onOptionsItemSelected(item)
    }

//    fun openActivity(cat: String){
//        var i = Intent(this, MainActivity::class.java)
//        i.putExtra("cat", cat)
//        startActivity(i)
//    }
}