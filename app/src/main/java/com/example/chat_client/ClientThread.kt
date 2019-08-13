/*Ekaterina Seikkinen 1806881
Client Thread class
new Socket (client) connection and input/output stream accessing
*/

package com.example.chat_client
import java.io.IOException
import java.io.PrintStream
import java.net.InetAddress
import java.net.Socket
import java.util.*


class ClientThread(private val mainActivity: MainActivity,private val ip: String, private val portNum:Int): Runnable{

    private lateinit var out: PrintStream
    private var input : Scanner? = null

    override fun run() {

        /*socket creation, input and output stream accessing*/

        try {

            val socket = Socket(InetAddress.getByName(ip),portNum)
            out = PrintStream(socket.getOutputStream(),true)                                                    //send data to server using an Output Stream
            input = Scanner(socket.getInputStream())                                                                     //read data from the server using an Input Stream

            while(true) {
                val line = input?.nextLine()
                mainActivity.newMessage(line as String)
            }

        } catch (e:IOException) {
            println("Got exception: ${e.message}")
        }
    }

    /*thread for sending a message to server for each message in chat*/

    fun sendMessage (message_s:String){
            Thread {out.println(message_s)}.start()
    }
}