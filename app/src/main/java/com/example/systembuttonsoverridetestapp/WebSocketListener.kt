/*
package com.example.systembuttonsoverridetestapp

import android.util.Log
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString


class WebSocketListener (
    private val viewModel: MainViewModel
):WebSocketListener() {
    private val TAG = "Test"
    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        viewModel.setConnectionStatus(true)
        webSocket.send("Android device connected and ready")
        Log.d(TAG, "onOpen:")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        viewModel.addMessage(Pair(false, text))
        Log.d(TAG, "onMessage: $text")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        viewModel.setConnectionStatus(false)
        Log.d(TAG, "onClosed: $code $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Log.d(TAG, "onFailure: ${t.message} $response")
        viewModel.setConnectionStatus(false)
        super.onFailure(webSocket, t, response)
    }
}*/
