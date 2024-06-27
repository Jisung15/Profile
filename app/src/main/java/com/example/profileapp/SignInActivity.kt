package com.example.profileapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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

        // SignUpActivity.kt에서 가져온 아이디와 비밀번호, 이름, 나이, 성별, MBTI를 연결해 놓은 EditText에 출력하는 부분. 회원가입 먼저 했고 로그인은 입력 안 했으니 가져올 때 까진 EditText가 비어있다.
        // 여기 코드는 밑에 있는 회원가입 페이지 가는 부분 실행하고 SignUpActivity.kt 갔다 와서 실행할 것이다.
        val resultValue = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result : ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val resultId = result.data?.getStringExtra("makeId")
                val resultPd = result.data?.getStringExtra("makePd")
                val resultName = result.data?.getStringExtra("makeName")
                val age = result.data?.getStringExtra("age")
                val mbti = result.data?.getStringExtra("mbti")
                val gender = result.data?.getStringExtra("gender")

                // EditText에 받아온 아이디와 비밀번호 출력
                loginId.setText(resultId ?: "")
                loginPd.setText(resultPd ?: "")

                if (loginId.text.isNotEmpty() && loginPd.text.isNotEmpty()) {
                    signButton.setOnClickListener {
                        val value = Intent(this, HomeActivity::class.java)
                        value.putExtra("loginId", resultId)                           // 아이디를 입력받는 EditText 에 입력한 아이디를 HomeActivity로 넘겨줌
                        value.putExtra("loginPd", resultPd)                           // 비밀번호를 입력받는 EditText 에 입력한 비밀번호를 HomeActivity로 넘겨줌
                        value.putExtra("NameValue", resultName)                       // 이름을 HomeActivity로 넘겨줌
                        value.putExtra("Age", age)                                    // 나이를 HomeActivity로 넘겨줌
                        value.putExtra("MBTI", mbti)                                  // mbti를 HomeActivity로 넘겨줌
                        value.putExtra("Gender", gender)                              // 성별을 HomeActivity로 넘겨줌
                        startActivity(value)
                    }
                }
            }
        }

        // 회원 가입 버튼 눌렀을 때 회원 가입 페이지로 이동
        makeProfileButton.setOnClickListener {
            val make = Intent(this, SignUpActivity::class.java)
            resultValue.launch(make)
        }

        // 로그인 버튼 눌렀을 때 프로필 페이지로 이동
        // 아이디나 비밀번호 중 하나라도 입력이 안 되어 있으면 "아이디/비밀번호를 확인해 주세요." 라는 내용의 토스트 메세지 출력
        signButton.setOnClickListener {
            if (loginId.text.isEmpty() || loginPd.text.isEmpty()) {
                Toast.makeText(this, "아이디/비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val home = Intent(this, HomeActivity::class.java)
                home.putExtra("loginId", loginId.text.toString())               // 아이디를 입력받는 EditText 에 입력한 Text를 HomeActivity로 넘겨줌
                home.putExtra("loginPd", loginPd.text.toString())               // 비밀번호를 입력받는 EditText 에 입력한 Text를 HomeActivity로 넘겨줌
                startActivity(home)
            }
        }
    }
}