package com.example.systembuttonsoverridetestapp

import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.net.URISyntaxException


class SocketHandler(
    private val viewModel: MainViewModel
) {

    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
            mSocket = IO.socket("https://ws.postman-echo.com/raw")
        } catch (e: URISyntaxException) {

        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.on("message event", onNewMessage);
        mSocket.connect()
        if (mSocket.isActive) {
            viewModel.setConnectionStatus(true)
        } else {
            viewModel.setConnectionStatus(false)
        }
    }

    private val onNewMessage =
        Emitter.Listener { args ->
            val text = args[0] as String
            viewModel.addMessage(Pair(false, text))
        }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
        viewModel.setConnectionStatus(false)
    }
}
