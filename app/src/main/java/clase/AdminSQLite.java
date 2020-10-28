package clase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLite extends SQLiteOpenHelper{

    //Constructor de base de datos
    public AdminSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //onCreate: define la configuracion inicial de mi base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE insumos (codigo int primary key, nombre text, precio float, stock int )");

    }

    //onUpgrade: implemento los cambios esquematicos de mi base de datos.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
