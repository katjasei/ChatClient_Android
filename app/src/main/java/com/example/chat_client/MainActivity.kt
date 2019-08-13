/*Ekaterina Seikkinen 1806881
Main Activity class
In this activity: create new activity, start new Thread (client thread), when activity is created new socket connection begin
*/

package com.example.chat_client
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var list = mutableListOf<NewMessage>()                                                                                //list of user's messages
    private var chatAdapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext,"Connection successful",Toast.LENGTH_SHORT).show()
        val chatClient= ClientThread(this,intent.extras["ip"] as String,(intent.extras["port"] as String).toInt())     //Chat client declaration
        Thread(chatClient).start()                                                                                                //New thread for reading from the server

        /*when user click a "send" button*/

        button.setOnClickListener{

            if(!editText2.text.toString().isEmpty()){
                chatClient.sendMessage(editText2.text.toString())                                                                 //use function "sendMessage" for sending message, that user enter
                editText2.text.clear()}
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

    override fun onStart() {

        super.onStart()

        /* Recycle View declaration
        in this case we use RecyclerView to display the list of our messages*/

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager= LinearLayoutManager(this)
        chatAdapter = Adapter(list)
        recyclerView.adapter = chatAdapter

        }

    /* new message (input) in RecyclerView (it is run in UI thread)*/

    fun newMessage(message:String){
        runOnUiThread{
                chatAdapter?.add(message)
            }
        }
    }


