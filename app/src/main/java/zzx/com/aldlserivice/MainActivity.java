package zzx.com.aldlserivice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import zzx.com.aldltest.MyAldl;

public class MainActivity extends AppCompatActivity {

    private MyAldl mserivice;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mserivice= MyAldl.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mserivice=null;
        }
    };
    private Button btn;
    private EditText text;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindservice();
        btn=(Button)findViewById(R.id.send);
        text=(EditText)findViewById(R.id.text);
        result=(TextView)findViewById(R.id.result);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    result.setText(mserivice.sendText(text.getText().toString()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void bindservice(){
        Intent intent=new Intent("com.zzx.myserivice");
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

}
