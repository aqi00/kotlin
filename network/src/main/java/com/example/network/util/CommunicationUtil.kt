package com.example.network.util

import java.util.ArrayList

import com.example.network.bean.CallRecord
import com.example.network.bean.Contact
import com.example.network.bean.SmsContent

import android.content.ContentProviderOperation
import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.net.Uri
import android.provider.CallLog
import android.provider.ContactsContract
import android.util.Log

object CommunicationUtil {
    private val TAG = "CommunicationUtil"
    private val mContactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
    private val mContactColumn = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

    fun readPhoneContacts(resolver: ContentResolver): Int {
        val contactArray = mutableListOf<Contact>()
        val cursor = resolver.query(mContactUri, mContactColumn, null, null, null)
        while (cursor.moveToNext()) {
            val contact = Contact()
            contact.phone = cursor.getString(0).replace("+86", "").replace(" ", "")
            contact.name = cursor.getString(1)
            Log.d(TAG, "${contact.name} ${contact.phone}")
            contactArray.add(contact)
        }
        cursor.close()
        return contactArray.size
    }

    fun readSimContacts(resolver: ContentResolver): Int {
        val simUri = Uri.parse("content://icc/adn")
        val contactArray = mutableListOf<Contact>()
        val cursor = resolver.query(simUri, mContactColumn, null, null, null)
        while (cursor.moveToNext()) {
            val contact = Contact()
            contact.phone = cursor.getString(0).replace("+86", "").replace(" ", "")
            contact.name = cursor.getString(1)
            Log.d(TAG, "${contact.name} ${contact.phone}")
            contactArray.add(contact)
        }
        cursor.close()
        return contactArray.size
    }

    fun addContacts(resolver: ContentResolver, contact: Contact) {
        val raw_uri = Uri.parse("content://com.android.contacts/raw_contacts")
        val values = ContentValues()
        // 添加一条联系人的主记录，并返回唯一的联系人编号
        val contactId = ContentUris.parseId(resolver.insert(raw_uri, values))
        val uri = Uri.parse("content://com.android.contacts/data")
        // 添加联系人姓名（要根据前面获取的id号）
        val name = ContentValues()
        name.put("raw_contact_id", contactId)
        name.put("mimetype", "vnd.android.cursor.item/name")
        name.put("data2", contact.name)
        resolver.insert(uri, name)
        // 添加联系人的手机号码
        val phone = ContentValues()
        phone.put("raw_contact_id", contactId)
        phone.put("mimetype", "vnd.android.cursor.item/phone_v2")
        phone.put("data2", "2")
        phone.put("data1", contact.phone)
        resolver.insert(uri, phone)
        // 添加联系人的电子邮箱
        val email = ContentValues()
        email.put("raw_contact_id", contactId)
        email.put("mimetype", "vnd.android.cursor.item/email_v2")
        email.put("data2", "2")
        email.put("data1", contact.email)
        resolver.insert(uri, email)
    }

    fun addFullContacts(resolver: ContentResolver, contact: Contact) {
        val raw_uri = Uri.parse("content://com.android.contacts/raw_contacts")
        val uri = Uri.parse("content://com.android.contacts/data")
        // 依次封装联系人主记录、联系人姓名、手机号码、电子邮箱的操作行为
        val op_main = ContentProviderOperation
                .newInsert(raw_uri).withValue("account_name", null).build()
        val op_name = ContentProviderOperation
                .newInsert(uri).withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/name")
                .withValue("data2", contact.name).build()
        val op_phone = ContentProviderOperation
                .newInsert(uri).withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                .withValue("data2", "2").withValue("data1", contact.phone)
                .build()
        val op_email = ContentProviderOperation
                .newInsert(uri).withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/email_v2")
                .withValue("data2", "2").withValue("data1", contact.email)
                .build()
        // 把以上四个操作行为组成行为队列，并一次性处理解决该行为队列
        val operations = mutableListOf(op_main, op_name, op_phone, op_email)
        resolver.applyBatch("com.android.contacts", operations as ArrayList<ContentProviderOperation>)
    }

    private var mSmsUri = Uri.parse("content://sms/inbox")
    private var mSmsColumn = arrayOf("address", "person", "body", "date", "type")

    fun readSms(resolver: ContentResolver, phone: String?, gaps: Int): Int {
        val smsArray = mutableListOf<SmsContent>()
        var selection = ""
        if (phone != null && phone.isNotEmpty()) {
            selection = "address='$phone'"
        }
        if (gaps > 0) {
            selection = "$selection${if (selection.isNotEmpty()) " and " else ""}" +
                    "date>${System.currentTimeMillis() - gaps*1000}"
        }
        val cursor = resolver.query(mSmsUri, mSmsColumn, selection, null, "date desc")
        while (cursor.moveToNext()) {
            val sms = SmsContent(address = cursor.getString(0),
                    person = cursor.getString(1),
                    body = cursor.getString(2),
                    date = DateUtil.formatDate(cursor.getLong(3)),
                    type = cursor.getInt(4))  //type=1表示收到的短信，type=2表示发送的短信
            Log.d(TAG, "${sms.address} ${sms.person} ${sms.date} ${sms.type} ${sms.body}")
            smsArray.add(sms)
        }
        cursor.close()
        return smsArray.size
    }

    private val mRecordUri = CallLog.Calls.CONTENT_URI
    private val mRecordColumn = arrayOf(CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER, CallLog.Calls.TYPE, CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.NEW)

    fun readCallRecord(resolver: ContentResolver, gaps: Int): Int {
        val recordArray = mutableListOf<CallRecord>()
        val selection = "date>${System.currentTimeMillis() - gaps*1000}"
        val cursor = resolver.query(mRecordUri, mRecordColumn, selection, null, "date desc")
        while (cursor.moveToNext()) {
            val record = CallRecord(name = cursor.getString(0),
                    phone = cursor.getString(1),
                    type = cursor.getInt(2),  //type=1表示接听，2表示拨出，3表示未接
                    date = DateUtil.formatDate(cursor.getLong(3)),
                    duration = cursor.getLong(4),
                    _new = cursor.getInt(5))
            Log.d(TAG, "${record.name} ${record.phone} ${record.type} ${record.date} ${record.duration}")
            recordArray.add(record)
        }
        cursor.close()
        return recordArray.size
    }

    private val mContactIdColumn = arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME)
    fun readAllContacts(resolver: ContentResolver): String {
        val contactArray = mutableListOf<Contact>()
        val cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI, mContactIdColumn, null, null, null)
        while (cursor.moveToNext()) {
            val contact = Contact(contactId = cursor.getString(0),
                                name = cursor.getString(1))
            contactArray.add(contact)
        }
        cursor.close()

        for (i in contactArray.indices) {
            val contact = contactArray[i]
            contact.phone = getColumn(resolver, contact.contactId,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                    ContactsContract.CommonDataKinds.Phone.NUMBER)
            contact.email = getColumn(resolver, contact.contactId,
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID,
                    ContactsContract.CommonDataKinds.Email.DATA)
            contactArray[i] = contact
            Log.d(TAG, "${contact.contactId} ${contact.name} ${contact.phone} ${contact.email}")
        }
        var result = ""
        for (item in contactArray) {
            result = "$result${item.name} ${item.phone}	${item.email}\n"
        }
        return result
    }

    private fun getColumn(resolver: ContentResolver, contactId: String,
                          uri: Uri, selection: String, column: String): String {
        val cursor = resolver.query(uri, null, "$selection=$contactId", null, null)
        var index = 0
        if (cursor.count > 0) {
            index = cursor.getColumnIndex(column)
        }
        var value = ""
        while (cursor.moveToNext()) {
            value = cursor.getString(index)
        }
        cursor.close()
        return value
    }

}
