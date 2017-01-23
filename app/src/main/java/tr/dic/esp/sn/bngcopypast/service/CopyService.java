package tr.dic.esp.sn.bngcopypast.service;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.SocketException;

import tr.dic.esp.sn.bngcopypast.client.ClientSocket;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

/**
 * Created by sonyvaio on 22/01/2017.
 */

public class CopyService extends Service {
    ClipboardManager.OnPrimaryClipChangedListener listener = new ClipboardManager.OnPrimaryClipChangedListener() {
        @Override
        public void onPrimaryClipChanged() {
            appliquerChangement();
        }
    };
    private void appliquerChangement() {
        ClipboardManager cb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (cb.hasPrimaryClip()) {
            if (cb.getPrimaryClip().getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                String text =cb.getPrimaryClip().getItemAt(0).getText().toString();
                Toast.makeText(getApplicationContext(), "text copie dans le clipboard",  Toast.LENGTH_LONG).show();
                SharedPreferences sharedPref = this.getApplicationContext().getSharedPreferences("save", Context.MODE_PRIVATE);//this.getSharedPreferences(Context.MODE_PRIVATE);
                try {

                    new ClientSocket().sendText(text, sharedPref.getString("add", null), 8585).getSocket().close();

                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "!erreur lors de l'envoi", Toast.LENGTH_LONG);
                }
            }
        }
    }

    @Override
    public void onCreate() {
        ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).addPrimaryClipChangedListener(listener);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getBaseContext(), "service demarre", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }
}
