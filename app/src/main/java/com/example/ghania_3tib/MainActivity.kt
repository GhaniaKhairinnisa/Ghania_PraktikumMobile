package com.example.ghania_3tib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ghania_3tib.databinding.ActivityMainBinding
import com.example.ghania_3tib.Home.pertemuan5.LimaActivity
import com.example.ghania_3tib.Home.pertemuan6.SixthActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
         }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val user = intent.getStringExtra("username")
        val pass = intent.getStringExtra("password")
        val umur = intent.getIntExtra("umur", 0)

        Log.e("Hasil", "umur $umur")

        binding.txtUsername.text = user
        binding.txtPassword.setText(pass)

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Item dihapus",
                Snackbar.LENGTH_LONG)
                .setAction("BATAL") { //bisa banyak aksi sesuai fungsi pop up
//                    // kembalikan item
//                    val intent = Intent(this, LoginActivity :: class.java)
//                    startActivity(intent)
                }
                .show()
        }

        binding.btnAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak " +
                        "bisa dikembalikan.")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Hapus") { dialog, _ ->
                    // proses hapus
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
        binding.btnKembali.setOnClickListener {
//            val intent = Intent(this, LoginActivity :: class.java)
//            startActivity(intent)

            finish() // jika halaman tidak digunakan lagi.
        }
        binding.btnToLima.setOnClickListener {
            val intent = Intent(this, LimaActivity :: class.java)
            startActivity(intent)
        }

        binding.btnToEnam.setOnClickListener {
            val intent = Intent(this, SixthActivity :: class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}