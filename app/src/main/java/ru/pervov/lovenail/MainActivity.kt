package ru.pervov.lovenail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.pervov.lovenail.clients_api.ClientApiInstanceGetter
import ru.pervov.lovenail.clients_api.models.Client

private const val PERMISSION_INSTALL_REQUEST = 200

class MainActivity : AppCompatActivity() {
    
    private val id = "123"
    private val client = Client(
        id = id,
        name = "test1",
        phoneNumber = "232323",
        comment = listOf(
            Client.Comment(
                date = "today",
                message = "test message",
                score = 10,
                id = "55"
            )
        )
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repo = ClientApiInstanceGetter.getInstance().getClientsRepository()
        Thread{
            repo.updateClient(
                client = client
            )
            val clientFromBD = repo.getClientById(id)
            Log.d("checkResult", "onCreate: $clientFromBD")
        }.start()
        
    }
}