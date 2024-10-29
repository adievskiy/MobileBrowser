package com.example.mobilebrowser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarMain: Toolbar
    private lateinit var gridViewGV: GridView
    private var list = mutableListOf(
        GridViewModal("Яндекс", R.drawable.ya, "http://www.ya.ru/"),
        GridViewModal("Рутуб", R.drawable.rutube, "https://rutube.ru/"),
        GridViewModal("ВКонтакте", R.drawable.vk, "https://vk.com/"),
        GridViewModal("GitHub", R.drawable.github, "https://github.com/"),
        GridViewModal("Developer Android", R.drawable.android, "https://developer.android.com/"),
        GridViewModal("Urban University", R.drawable.onlayn_universitet_urban_university, "https://urban-university.ru/")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Мобильный браузер"

        gridViewGV = findViewById(R.id.gridViewGV)

        val adapter = GridViewAdapter(list = list, this@MainActivity)
        gridViewGV.adapter = adapter

        gridViewGV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("URL", list[position].url)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuExit -> finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }
}