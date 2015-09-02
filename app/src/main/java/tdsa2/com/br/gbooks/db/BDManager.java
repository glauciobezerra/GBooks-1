package tdsa2.com.br.gbooks.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import tdsa2.com.br.gbooks.model.Biblioteca;

/**
 * Created by ricardo on 02/09/2015.
 */
public class BDManager extends SQLiteOpenHelper {

    public BDManager(Context ctx){
        super(ctx, ConstantesBD.DB_NAME, null, ConstantesBD.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConstantesBD.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void insertLibrary(Biblioteca lib){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ConstantesBD.COLUMN_BIBLIOTECAS_NOME, lib.nome);
        cv.put(ConstantesBD.COLUMN_BIBLIOTECAS_ENDERECO, lib.endereco);
        cv.put(ConstantesBD.COLUMN_BIBLIOTECAS_LATITUDE, lib.latitude);
        cv.put(ConstantesBD.COLUMN_BIBLIOTECAS_LONGITUDE, lib.longitude);

        db.insert(ConstantesBD.TABLE_NAME, null, cv);

        db.close();
    }

    public List<Biblioteca> getLibraries(){
        SQLiteDatabase db = getReadableDatabase();

        List<Biblioteca> libs = new ArrayList<Biblioteca>();

        Cursor cursor = db.query(ConstantesBD.TABLE_NAME, null, null, null, null, null, ConstantesBD.COLUMN_BIBLIOTECAS_NOME);
        while (cursor.moveToNext()){
            Biblioteca lib = new Biblioteca();
            lib.endereco = cursor.getString(cursor.getColumnIndex(ConstantesBD.COLUMN_BIBLIOTECAS_ENDERECO));
            lib.nome = cursor.getString(cursor.getColumnIndex(ConstantesBD.COLUMN_BIBLIOTECAS_NOME));
            lib.latitude = cursor.getDouble(cursor.getColumnIndex(ConstantesBD.COLUMN_BIBLIOTECAS_LATITUDE));
            lib.longitude = cursor.getDouble(cursor.getColumnIndex(ConstantesBD.COLUMN_BIBLIOTECAS_LONGITUDE));
            libs.add(lib);
        }

        cursor.close();
        db.close();

        return libs;
    }

    public void cleanLibraries(){
        SQLiteDatabase db = getWritableDatabase();

        db.delete(ConstantesBD.TABLE_NAME, null, null);

        db.close();
    }
}
