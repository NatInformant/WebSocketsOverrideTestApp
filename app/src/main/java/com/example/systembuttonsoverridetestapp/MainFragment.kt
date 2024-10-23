package com.example.systembuttonsoverridetestapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.systembuttonsoverridetestapp.databinding.FragmentMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var webSocketListener: WebSocketListener
    private val okHttpClient = OkHttpClient()
    private var webSocket: WebSocket? = null
    private val binding: FragmentMainBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webSocketListener = WebSocketListener(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.socketStatus.observe(viewLifecycleOwner){
            binding.connectionStatus.text = if (it) "connected" else "disconnected"
        }

        var text = ""
        viewModel.messages.observe(viewLifecycleOwner) {
            text += "${if (it.first) "You: " else "Other: "} ${it.second}\n"

            binding.conversation.text = text
        }

        binding.connectBtn.setOnClickListener{
            webSocket = okHttpClient.newWebSocket(createRequest(), webSocketListener)
        }

        binding.disconnectBtn.setOnClickListener{
            webSocket?.close(1000,"Canceled manually")
        }

        binding.sendBtn.setOnClickListener{
            webSocket?.send(binding.messageText.text.toString())
            viewModel.addMessage(Pair(true, binding.messageText.text.toString()))
        }
    }
    private fun createRequest(): Request {
        val websocketURL = "wss://ws.postman-echo.com/raw"

        return Request.Builder()
            .url(websocketURL)
            .build()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        okHttpClient.dispatcher.executorService.shutdown()
    }
}