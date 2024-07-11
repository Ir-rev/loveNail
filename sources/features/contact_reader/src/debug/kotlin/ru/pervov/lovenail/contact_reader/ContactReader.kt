package ru.pervov.lovenail.contact_reader

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pervov.lovenail.clients_api.models.Client
import java.util.UUID

private const val TAG = "[ContactReader]"

class ContactReader {

    suspend fun readContact(context: Context): List<Client> {
        return withContext(Dispatchers.IO) {
            val phones = context.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
            ) ?: return@withContext emptyList()
            try {
                val contactList = mutableListOf<Client>()
                while (phones.moveToNext()) {
                    val name =
                        phones.getString(
                            phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                        )
                    val phoneNumber =
                        phones.getString(
                            phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        )
                    contactList.add(
                        Client(
                            name = name,
                            phoneNumber = phoneNumber,
                            id = UUID.randomUUID().toString(),
                            comment = emptyList()
                        )
                    )
                }
                return@withContext contactList
            } catch (e: Throwable) {
                Log.e(TAG, "readContact: $e")
                return@withContext emptyList()
            }
        }
    }
}