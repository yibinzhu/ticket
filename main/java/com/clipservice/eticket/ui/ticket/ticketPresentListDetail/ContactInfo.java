package com.clipservice.eticket.ui.ticket.ticketPresentListDetail;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

public class ContactInfo {
    public String id;
    public String name;
    public String phone;
    public String email;

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public static ContactInfo getPickContact(Context context, Uri uri) {
        ContactInfo info = new ContactInfo();
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        String num = "";
        if (cursor != null && cursor.moveToNext()) {
            info.id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            info.name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            //替换掉不合法的字符
            num = num.replace("-", "");
            num = num.replace(" ", "");
            info.phone = num;
            cursor.close();
        }
        Cursor emailCur = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = "+info.id,
                null, null);
        if (emailCur != null) {
            while (emailCur.moveToNext()) {
                info.email = emailCur.getString(
                        emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            }
            emailCur.close();
        }
        Log.d("getPickContact", "ID:" + info.id + " name:" + info.name + " num:" + info.phone + " email:" + info.email);
        return info;
    }
}
