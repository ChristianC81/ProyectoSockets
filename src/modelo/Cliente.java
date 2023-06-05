/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author chris
 */
public class Cliente {

    private Socket cliente;

    public Cliente() {
        cliente = new Socket();
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cliente c = new Cliente();
        DataInputStream entrada;
        DataOutputStream salida;

        try {
            c.setCliente(new Socket("127.0.0.1", 8012));
            //Conexion y entrada de datos con el servidor 
            entrada = new DataInputStream(c.getCliente().getInputStream());
            //Pregunta del servidor 
            boolean parar = true;

            while (parar) {
                System.out.println(entrada.readUTF());
                //Salida de datos con el servidor 
                salida = new DataOutputStream(c.getCliente().getOutputStream());
                //Se agrega la respuesta por consola a la variable string
                String respuestacliente = sc.next();

                //Se escribe en salida al servidor la varible con el valor clave
                if (respuestacliente.toLowerCase().equals("parar")) {
                    System.out.println("Gracias por preferinos!");
                    parar = false;
                } else {

                    salida.writeUTF(respuestacliente);

                    //Ingreso del valor con la clave enviada al servidor
                    System.out.println(entrada.readUTF() + "\n");
                }

            }

        } catch (IOException e) {
            System.out.println("!Atención¡ Es necesario que el servidor este activo");
        }

    }
}
