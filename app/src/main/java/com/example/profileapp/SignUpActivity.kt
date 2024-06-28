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
        val age = findViewById<EditText>(R.id.et_age_value)
        val mbti = findViewById<EditText>(R.id.et_MBTI_value)
        val gender = findViewById<EditText>(R.id.et_gender_value)

        // 회원가입 완료 버튼 누르면 다시 로그인 화면으로 이동. 이때 아이디와 비밀번호, 나이, 성별, 이름, MBTI 입력한 걸 가지고 가도록 설정
        // 이름이나 아이디, 비밀번호, 나이, 성별, MBTI 중 하나라도 입력하지 않으면 "입력되지 않은 정보가 있습니다." 라는 토스트 메세지 출력
        signUpButton.setOnClickListener {
            if (makeName.text.isEmpty() || makeId.text.isEmpty() || makePd.text.isEmpty() || age.text.isEmpty() || mbti.text.isEmpty() || gender.text.isEmpty()) {

                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()

            } else {
                val userClass = UserClass("${makeName.text}", "${makeId.text}", "${makePd.text}", "${age.text}", "${mbti.text}", "${gender.text}")         // 데이터 클래스 설정
                val back = Intent(this, SignInActivity::class.java)

                // 나이 입력을 잘못 했는지 검사하기 위해 예외 처리 추가
                try {
                    if (age.text.toString().toInt() in 1..100) {
                        back.putExtra("age", userClass.age)
                    } else {
                        Toast.makeText(this, "입력 가능한 나이 숫자 범위를 벗어났습니다.", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                } catch (e:NumberFormatException){
                    Toast.makeText(this, "나이 입력이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // 성별 입력을 잘못 했는지 검사하기 위해 예외 처리 추가
                if (gender.text.toString() == "남성" || gender.text.toString() == "여성") {
                    back.putExtra("gender", userClass.gender)
                } else {
                    Toast.makeText(this, "성별 입력이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // 각 MBTI를 대문자와 소문자로 나눠서 정리를 해놓은 배열. 이 안에 mbti.text.toString() 값이 없으면 아래에 있는 if문을 실행하지 않고 else를 실행한다.
                val mbtiList = listOf(
                    listOf(
                        "ISTJ", "ISTP", "ISFP", "ISFJ", "INTJ", "INTP", "INFP", "INFJ", "ESTJ", "ESTP", "ESFP", "ESFJ", "ENTJ", "ENTP", "ENFP", "ENFJ"
                    ),
                    listOf(
                        "istj", "istp", "isfp", "isfj", "intj", "intp", "infp", "infj", "estj", "estp", "esfp", "esfj", "entj", "entp", "enfp", "enfj"
                    )
                )

                // MBTI 입력을 잘못 했는지 검사하기 위해 예외 처리 추가
                if (mbti.text.toString() in mbtiList[0] || mbti.text.toString() in mbtiList[1]) {
                    back.putExtra("mbti", userClass.mbti)
                } else {
                    Toast.makeText(this, "MBTI 입력이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                back.putExtra("makeId", userClass.id)
                back.putExtra("makePd", userClass.pd)
                back.putExtra("makeName", userClass.name)
                setResult(RESULT_OK, back)
                finish()
            }
        }
    }
}