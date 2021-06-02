package com.ceoc.basededatosclientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class AdminBD extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME= "clientesBD.db";
    private static final String TABLE_CLIENTS= "clientes";
    private static final String COLUMN_ID= "_id";
    private static final String COLUMN_NAME= "_nombre";
    private static final String COLUMN_PHONE= "_telefono";
    private static final String COLUMN_DIRECTION= "_direccion";
    private static final String COLUMN_MAIL= "_correo";

    public AdminBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear tabla
        String CREATE_TABLE_CLIENTS = "CREATE TABLE " + "clientes" + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_PHONE + " TEXT, " + COLUMN_DIRECTION + " TEXT, " + COLUMN_MAIL + " TEXT" + ")";
        //Crear tabla
        db.execSQL(CREATE_TABLE_CLIENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Borrar tabla de clientes si existe
        String DROP_TABLE_CLIENTS= "DROP TABLE IF EXISTS " + TABLE_CLIENTS;
        db.execSQL(DROP_TABLE_CLIENTS);
        //Crear nueva tabla
        onCreate(db);

    }
    //Agregar cliente
    long addClient(Cliente cliente){
        //Crear nuevo contenerdor de valores
        ContentValues values= new ContentValues();
        //Agregar datos del cliente
        values.put(COLUMN_NAME, cliente.get_nombre());
        values.put(COLUMN_PHONE, cliente.get_telefono());
        values.put(COLUMN_DIRECTION, cliente.get_direccion());
        values.put(COLUMN_MAIL, cliente.get_correo());
        //Obtener objeto de la base de datos
        SQLiteDatabase database= this.getWritableDatabase();
        //Insertar registros en la tabla
        long result= database.insert(TABLE_CLIENTS, null, values);
        //Cerrar base de datos
        database.close();
        return result;
    }
    //Buscar cliente por nombre
    Cliente findClient(String name){
        //Definir query de busqueda
        String query= "SELECT * FROM " + TABLE_CLIENTS + " WHERE " + COLUMN_NAME + " = " + "\"" + name + "\"";
        //Obtener objeto de la base de datos
        SQLiteDatabase database= this.getWritableDatabase();
        //Realizar busqueda
        Cursor cursor= database.rawQuery(query, null);
        //Crear objeto de tipo Cliente
        Cliente cliente= new Cliente();
        //Verificar si se encontró un registro
        if(cursor.moveToFirst()){
            //Obtener ID con la posición 0
            cliente.set_id(Integer.parseInt(cursor.getString(0)));
            //Obtner nombre con la posición 1
            cliente.set_nombre(cursor.getString(1));
            //Obtener teléfono con la posición 2
            cliente.set_telefono(cursor.getString(2));
            //Obtener dirección con la posición 3
            cliente.set_direccion(cursor.getString(3));
            //Obtener correo con la posición 4
            cliente.set_correo(cursor.getString(4));
            //Cerrar cursor
            cursor.close();
        }else{
            cliente = null;
        }
        database.close();
        //Retornar cliente
        return cliente;
    }
    //Borrar cliente
    boolean deleteClient(String name){
        //Crear valor de retorno como falso
        boolean result= false;
        //Buscar cliente utilizando el método findClient()
        Cliente cliente= findClient(name);
        //Si es distinto de null entonces se encontró un registro
        if(cliente != null){
            //Crear query para eliminar el registro
            String query= "DELETE FROM " + TABLE_CLIENTS + " WHERE " + COLUMN_NAME + " = " + "\"" + name + "\"";
            //Obtener objeto de la base de datos
            SQLiteDatabase database= this.getWritableDatabase();
            //Ejecutar query para eliminar
            database.execSQL(query);
            //Cerrar base de datos
            database.close();
            //Retornar resultado como verdadero
            result= true;
        }
        return result;
    }
}
