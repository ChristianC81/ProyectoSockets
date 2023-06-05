/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author chris
 */

public class Servidor {

    private ServerSocket servidor;
    private int puerto;

    public Servidor() {
    }

    public Servidor(int puerto) {
        this.puerto = puerto;
        try {
            this.servidor = new ServerSocket(puerto);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public static void main(String[] args) {

        Servidor s = new Servidor(8012);
        DataOutputStream salida;
        DataInputStream entrada;
        try {
            Socket clienteconexion = s.getServidor().accept();
            System.out.println(clienteconexion.getInetAddress());
            salida = new DataOutputStream(clienteconexion.getOutputStream());
            //salida.writeUTF( "Solicitud aceptada :D");
            HashMap<String, String> misPalabras = new HashMap<>();

            // Agregar palabras como claves y valores al HashMap
            misPalabras.put("Exuberante", "Abundante, lozano, frondoso");
            misPalabras.put("Melancolía", "Sentimiento de tristeza y pesadumbre sin causa aparente");
            misPalabras.put("Irascible", "Propenso a la ira o fácilmente irritable");
            misPalabras.put("EfImero", "Que dura poco tiempo o que es pasajero");
            misPalabras.put("Ambiguo", "Que puede entenderse de distintas maneras o que tiene más de una interpretación posible");
            misPalabras.put("Nefasto", "Desgraciado, funesto, que ocasiona daño o desgracia");
            misPalabras.put("Meticuloso", "Detallista, minucioso, que presta atención a cada detalle");
            misPalabras.put("Perplejidad", "Estado de confusión o de incertidumbre ante algo difícil de entender o explicar");
            misPalabras.put("Loable", "Digno de alabanza, meritorio, virtuoso");
            misPalabras.put("VORAZ", "Que devora con avidez o que tiene un apetito insaciable");

         
            
            while (true) {
                 boolean palabraenc = false;
                entrada = new DataInputStream(clienteconexion.getInputStream());
                salida.writeUTF("----------------------------------------\n Bienvenido a un dicionario de palabras  \n Consulte su palabra:");
                String valor = entrada.readUTF();
                //Recorro y obtengo las claves del HashMap en una variable string 
                for (String clave : misPalabras.keySet()) {
                  // para compararlo en minusculas con la valor de entrada en minusculas
                    if (valor.equalsIgnoreCase(clave)) {
                        System.out.println("Se encontro:" + clave);
                        System.out.println("Con el valor ingresado: " + valor);
                        //Si en el hashmap existe la clave que tenemos del bucle for  
                        if (misPalabras.containsKey(clave)) {
                            //nos da de salida al cliente el valor de la clave
                            salida.writeUTF(misPalabras.get(clave));
                            palabraenc = true;
                            break;
                        }
                    }
                }
                if (!palabraenc) {
                    salida.writeUTF("No existe la palabra en el Diccionario");
                }
                 
            }
            //Crear un hashmap generamos 10 registros con su clave (Palabra) y valor (concepto)
        } catch (NullPointerException e) {
            System.out.println("El servidor ya se encuentra en ejecución");
        } catch (IOException ex) {
            System.out.println("La direccion del Puerto esta en uso");
        }
    }
}
