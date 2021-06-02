package com.ceoc.basededatosclientes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Definir texto para identificador
    TextView txtID;
    //Definir campos necesarios para registrar al cliente
    EditText editName;
    EditText editPhone;
    EditText editAddress;
    EditText editMail;
    //Definir manejador de la base de datos
    AdminBD adminBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID=findViewById(R.id.tv_id);
        editName= findViewById(R.id.et_nombre);
        editPhone= findViewById(R.id.et_telefono);
        editAddress= findViewById(R.id.et_direccion);
        editMail= findViewById(R.id.et_correo);
        adminBD = new AdminBD(this, null, null, 1);
    }
    public void addClient(View view){
        //Definir datos
        String name  = editName.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        String address = editAddress.getText().toString().trim();
        String mail = editMail.getText().toString().trim();
        //Crear el objeto de tipo Cliente
        Cliente cliente = new Cliente(name, phone, address, mail);
        //Agregar el nuevo cliente a la base de datos
        long result = adminBD.addClient(cliente);
        if(result == -1){
            txtID.setText(R.string.noAdded);
        }else{
            txtID.setText(R.string.Added);
        }
        editName.setText("");
        editPhone.setText("");
        editAddress.setText("");
        editMail.setText("");
    }
    //Buscar cliente
    public void findClient(View view){
        //Definir nombre
        String name = editName.getText().toString().trim();
        //Buscar cliente
        Cliente cliente = adminBD.findClient(name);
        //Verificar si se encontró un cliente
        if(cliente == null){
            txtID.setText(R.string.noEncontrado);
            editName.setText("");
            editPhone.setText("");
            editAddress.setText("");
            editMail.setText("");
        }else{
            txtID.setText(String.valueOf(cliente.get_id()));
            editName.setText(cliente.get_nombre());
            editPhone.setText(cliente.get_telefono());
            editAddress.setText(cliente.get_direccion());
            editMail.setText(cliente.get_correo());
        }
    }
    //Borrar cliente
    public void deleteClient(View view){
        //Definir nombre
        String name = editName.getText().toString().trim();
        //Verificar si se borró al cliente
        if(adminBD.deleteClient(name)){
            txtID.setText(R.string.Eliminado);
        }else{
            txtID.setText(R.string.noEncontrado);
        }
        editName.setText("");
        editPhone.setText("");
        editAddress.setText("");
        editMail.setText("");
    }
}