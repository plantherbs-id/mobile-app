package com.plantherbs.app.ui.verification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.telephony.SmsMessage
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.plantherbs.app.R
import com.plantherbs.app.ui.login.LoginActivity

class VerificationActivity : AppCompatActivity() {

    private lateinit var smsReceiver: BroadcastReceiver
    private lateinit var inputKodeOtp: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        inputKodeOtp = findViewById(R.id.input_kode_otp)
        val btnVerify: Button = findViewById(R.id.btn_register)

        // Set up BroadcastReceiver untuk mendengarkan SMS
        smsReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val bundle = intent.extras
                if (bundle != null) {
                    val pdus = bundle.get("pdus") as Array<*>
                    for (i in pdus.indices) {
                        val message = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                        val sender = message.displayOriginatingAddress
                        val messageBody = message.displayMessageBody

                        // Lakukan pemrosesan untuk mengekstrak kode OTP dari pesan
                        // Sesuaikan dengan format pesan SMS yang Anda terima
                        // Misalnya, jika kode OTP ada dalam pesan, ekstrak kode tersebut
                        val otpCode = extractOtpCode(messageBody)

                        // Setel nilai kode OTP di EditText
                        inputKodeOtp.setText(otpCode)
                    }
                }
            }
        }

        // Daftarkan BroadcastReceiver untuk mendengarkan pesan SMS
        val intentFilter = IntentFilter("android.provider.Telephony.SMS_RECEIVED")
        registerReceiver(smsReceiver, intentFilter)

        // Setelah verifikasi selesai, arahkan ke LoginActivity
        btnVerify.setOnClickListener {
            val intent = Intent(this@VerificationActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun extractOtpCode(messageBody: String): String {

        val otpLength = 6
        return if (messageBody.length >= otpLength) {
            messageBody.substring(messageBody.length - otpLength)
        } else {
            ""
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister BroadcastReceiver ketika aktivitas dihancurkan
        unregisterReceiver(smsReceiver)
    }
}
