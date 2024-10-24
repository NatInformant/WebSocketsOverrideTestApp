package com.example.systembuttonsoverridetestapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.systembuttonsoverridetestapp.databinding.FragmentMainBinding
import io.socket.client.Socket
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var webSocketHandler: SocketHandler

    private var webSocket: Socket? = null

    private val binding: FragmentMainBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webSocketHandler = SocketHandler(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.socketStatus.observe(viewLifecycleOwner) {
            binding.connectionStatus.text = if (it) "connected" else "disconnected"
        }

        var text = ""
        viewModel.messages.observe(viewLifecycleOwner) {
            text += "${if (it.first) "You: " else "Other: "} ${it.second}\n"

            binding.conversation.text = text
        }

        binding.connectBtn.setOnClickListener {
            createRequest()
            webSocket = webSocketHandler.getSocket()
        }

        binding.disconnectBtn.setOnClickListener {
            webSocketHandler.closeConnection()
        }

        binding.sendBtn.setOnClickListener {

            if (webSocket!!.isActive) {
                viewModel.setConnectionStatus(true)
            } else {
                viewModel.setConnectionStatus(false)
            }

            webSocket?.emit("message event", binding.messageText.text.toString())
            viewModel.addMessage(Pair(true, binding.messageText.text.toString()))
        }
    }

    private fun createRequest() {
        webSocketHandler.setSocket()
        webSocketHandler.establishConnection()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webSocketHandler.closeConnection()
    }
}
