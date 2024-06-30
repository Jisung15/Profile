package com.example.profileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ContentInfoCompat.Flags
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // activity_home.xml에서 만든 버튼, TextView, ImageView 연결 (참 많기도 하다...)
        val homeButton = findViewById<Button>(R.id.btn_finish)
        val profilePageTitle = findViewById<TextView>(R.id.tv_profile_title)
        val homeImage = findViewById<ImageView>(R.id.iv_home_title)
        val password = findViewById<TextView>(R.id.tv_password)
        val name = findViewById<TextView>(R.id.tv_home_name)
        val ageEdit = findViewById<TextView>(R.id.tv_home_age)
        val mbtiEdit = findViewById<TextView>(R.id.tv_home_MBTI)
        val genderEdit = findViewById<TextView>(R.id.tv_home_gender)

        val loginId = intent.getStringExtra("loginId")                              // SignInActivity.kt에서 아이디를 입력받은 것을 넘겨주는데 그걸 받는 부분
        val loginPd = intent.getStringExtra("loginPd")                              // 비밀번호를 받기
        val loginName = intent.getStringExtra("NAME")?: "김성진"                     // 이름을 받기
        val age = intent.getStringExtra("AGE")?: "만 22"                            // 나이를 받기
        val mbti = intent.getStringExtra("MBTI")?: "ISTJ"                           // mbti를 받기
        val gender = intent.getStringExtra("GENDER")?: "남성"                        // 성별을 받기

        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()        // 그것들을 받아서 이 페이지로 넘어왔다는 건 로그인 성공이라는 뜻이므로 로그인 성공 토스트 메세지 출력

        profilePageTitle.text = "${loginId} 님의 프로필"                                   // 제목 TextView에 받아온 아이디를 프로필 제목으로 출력
        password.text = "비밀번호 : ${loginPd}"                                           // 비밀번호를 출력
        name.text = "이름 : ${loginName}"                                                // 이름을 출력
        ageEdit.text = "나이 : ${age}세"                                                 // 나이 출력
        genderEdit.text = "성별 : ${gender}"                                             // 성별 출력

        val mbtiList = listOf(
            listOf(
                "istj", "istp", "isfp", "isfj", "intj", "intp", "infp", "infj"
            ),
            listOf(
                "estj", "estp", "esfp", "esfj", "entj", "entp", "enfp", "enfj"
            )
        )

        if (mbti in mbtiList[0] || mbti in mbtiList[1]) {
            mbtiEdit.text = "MBTI : ${mbti.uppercase()}"                                 // MBTI 출력 (소문자면 대문자로 변환)
        } else {
            mbtiEdit.text = "MBTI : ${mbti}"                                             // MBTI 출력
        }

        val random = Random.nextInt(5) + 1                                          // 랜덤으로 1부터 5까지 정수 중 하나를 고름

        // 그렇게 랜덤으로 선택된 숫자가 무엇인가에 따라 랜덤으로 이미지 출력
        when (random) {
            1 -> homeImage.setImageResource(R.drawable.welcome)
            2 -> homeImage.setImageResource(R.drawable.add)
            3 -> homeImage.setImageResource(R.drawable.korea)
            4 -> homeImage.setImageResource(R.drawable.emperor)
            5 -> homeImage.setImageResource(R.drawable.application)
        }

        // 종료 버튼 누르면 메인 화면을 종료하고 다시 로그인 화면으로 돌아가게 설정
        homeButton.setOnClickListener {
            finish()
        }
    }
}