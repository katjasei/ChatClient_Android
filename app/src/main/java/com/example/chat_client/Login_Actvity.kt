/*Ekaterina Seikkinen 1806881
Login_Activity class
In this activity user enter Ip address and port number and then press button "Connect" for connection with server
I use "intent" method to communicate between two activities(to move from LoginActivity to MainActivity) */

package com.example.chat_client
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.logout_activity.*

class LoginActvity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logout_activity)

        button2.setOnClickListener {

            if(!ip_text.text.toString().isEmpty() && !port_text.text.toString().isEmpty()){
            connection()}
            else {Toast.makeText(applicationContext,"Write IP-address and port",Toast.LENGTH_SHORT).show()}         //if user doesn't enter ip-address or port number, pop-up message shown

        }
    }
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        /* Checks the orientation of the screen*/

        if (newConfig!!.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()

        }
    }

    private fun connection (){

        val ipAddress = ip_text.text.toString()
        val portNumber = port_text.text.toString()

        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("ip",ipAddress)                                                    //use "putExtra" command for passing data to another activity
        intent.putExtra("port",portNumber)                                                 //from LoginActivity to MainActivity
        startActivity(intent)

    }

}