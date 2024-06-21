package com.example.profileapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // activity_sign_up.xml에서 만든 버튼, EditText 연결
        val signUpButton = findViewById<Button>(R.id.btn_make_profile)
        val makeName = findViewById<EditText>(R.id.et_make_profile_name)
        val makeId = findViewById<EditText>(R.id.et_make_profile_id)
        val makePd = findViewById<EditText>(R.id.et_make_profile_password)

        // 회원가입 완료 버튼 누르면 다시 로그인 화면으로 이동
        // 이름이나 아이디, 비밀번호 중 하나라도 입력하지 않으면 "입력되지 않은 정보가 있습니다." 라는 토스트 메세지 출력
        signUpButton.setOnClickListener {
            if (makeName.text.isEmpty() || makeId.text.isEmpty() || makePd.text.isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                finish()
            }
        }
    }
}