package bw.mymis.app.sqlitedemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import bw.mymis.app.sqlitedemoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    SQLiteDatabase sdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 開啟或建立資料庫呼叫

                sdb = openOrCreateDatabase(
                        binding.txtDBName.getText().toString(),
                        MODE_PRIVATE,
                        null
                        );
                //使用完畢後 ( OnDestroy 要關閉資料庫 以免資料 loss )
                sdb.close();
            }
        });

        binding.btnCreateTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create String:
                String createTB=
                        "Create Table room( _id integer, name text, floor text, capacity integer, projector integer);";
                // 開啟 或是 建立新的 資料庫
                sdb = openOrCreateDatabase(
                        binding.txtDBName.getText().toString(),
                        MODE_PRIVATE,
                        null
                );
                sdb.execSQL(createTB);
                sdb.close();
            }
        });
        binding.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create String:
                String insert1=
                        "insert into room values ( 2, '2F會議室','2F',20,0);";

                // 開啟 或是 建立新的 資料庫
                sdb = openOrCreateDatabase(
                        binding.txtDBName.getText().toString(),
                        MODE_PRIVATE,
                        null
                );
                sdb.execSQL(insert1);
                sdb.close();
            }
        });
        binding.btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdb = openOrCreateDatabase(
                        binding.txtDBName.getText().toString(),
                        MODE_PRIVATE,
                        null
                );
                Cursor cursor = sdb.rawQuery("select * from room;",null);
                int count = cursor.getCount();
                Toast.makeText(MainActivity.this, "共查詢到 " + count + " 筆會議室資料", Toast.LENGTH_LONG).show();
                cursor.moveToFirst();
                Toast.makeText(MainActivity.this, "會議室名稱: " + cursor.getString(1), Toast.LENGTH_SHORT).show();
                sdb.close();
            }

        });
    }
}