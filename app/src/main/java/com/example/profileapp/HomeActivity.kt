package com.example.profileapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        // activity_home.xml에서 만든 버튼, TextView, ImageView 연결
        val homeButton = findViewById<Button>(R.id.btn_finish)
        val loginIdValue = findViewById<TextView>(R.id.tv_home_id)
        val profilePageTitle = findViewById<TextView>(R.id.tv_profile_title)
        val homeImage = findViewById<ImageView>(R.id.iv_home_title)

        // SignInActivity.kt에서 아이디를 입력받은 것을 넘겨주는데 그걸 받는 부분
        val loginId = intent.getStringExtra("ID")

        // 그걸 받아서 이 페이지로 넘어왔다는 건 로그인 성공이라는 뜻이므로 로그인 성공 토스트 메세지 출력
        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

        // 제목 TextView에 받아온 아이디를 프로필 제목으로 출력
        profilePageTitle.text = "${loginId}님의 프로필"

        // 아이디를 출력하는 TextView에 받아온 아이디를 출력
        loginIdValue.text = "아이디 : ${loginId}"

        // 랜덤으로 1부터 5까지 정수 중 하나를 고름
        val random = Random.nextInt(5) + 1

        // 그렇게 랜덤으로 선택된 숫자가 무엇인가에 따라 랜덤으로 이미지 출력
        when (random) {
            1 -> homeImage.setImageResource(R.drawable.user)
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