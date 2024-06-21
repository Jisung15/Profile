package com.example.profileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) {v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // activity_sign_in.xml에서 생성한 버튼, EditText 연결
        val makeProfileButton = findViewById<Button>(R.id.btn_make_profile_start)
        val signButton = findViewById<Button>(R.id.btn_login)
        val loginId = findViewById<EditText>(R.id.et_login_id)
        val loginPd = findViewById<EditText>(R.id.et_login_password)

        // 회원 가입 버튼 눌렀을 때 회원 가입 페이지로 이동
        makeProfileButton.setOnClickListener {
            val make = Intent(this, SignUpActivity::class.java)
            startActivity(make)
        }

        // 로그인 버튼 눌렀을 때 프로필 페이지로 이동
        // 아이디나 비밀번호 중 하나라도 입력이 안 되어 있으면 "아이디/비밀번호를 확인해 주세요." 라는 내용의 토스트 메세지 출력
        signButton.setOnClickListener {
            if (loginId.text.isEmpty() || loginPd.text.isEmpty()) {
                Toast.makeText(this, "아이디/비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val home = Intent(this, HomeActivity::class.java)
                home.putExtra("ID", loginId.text.toString())               // 아이디를 입력받는 EditText 에 입력한 Text를 HomeActivity로 넘겨줌
                startActivity(home)
            }
        }
    }
}