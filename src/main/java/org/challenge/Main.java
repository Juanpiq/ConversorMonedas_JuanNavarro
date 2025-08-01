package org.challenge;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final String API_KEY = "2278893e8dc8a69b635e0c81";
    public static void main(String[] args) {
        /*Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(persona);*/
        Scanner in = new Scanner(System.in);
        int op = 1;
        String url;

        while(op != 7){
            try{
                System.out.println("""
                                      Sea Bienvenido/a al Conversor de Monedas
                                      1) Dólar =>> Peso Argentino
                                      2) Peso Argentino =>> Dólar
                                      3) Dólar =>> Real brasileño
                                      4) Real brasileño =>> Dólar
                                      5) Dólar =>> Peso Colombiano
                                      6) Peso Colombiano =>> Dólar
                                      7) Salir
                                   """);
                System.out.print("Elija una opción válida: ");
                op = in.nextInt();
                in.nextLine();
                if(op >= 1 && op <= 7){
                    if(op == 7)
                        System.out.println("¡Hasta luego!");
                    else{
                        url = EscogerOpcion(in, op);
                    }
                } else {
                    System.out.println("Opción no disponible, vuelva a intentarlo\n");
                    op = 1;
                    in.nextLine();
                }
            }catch(InputMismatchException e){
                System.out.println("Ingrese un numero válido");
                in.nextLine();
                op = 1;
            }finally{
                System.out.println("\n");
            }
        }
    }

    static String EscogerOpcion(Scanner in, int op){
        String moneda1 = null, moneda2 = null;

        switch(op){
            case 1:{
                moneda1 = "USD";
                moneda2 = "ARS";
                break;
            }
            case 2:{
                moneda1 = "ARS";
                moneda2 = "USD";
                break;
            }
            case 3:{
                moneda1 = "USD";
                moneda2 = "BRL";
                break;
            }
            case 4:{
                moneda1 = "BRL";
                moneda2 = "USD";
                break;
            }
            case 5:{
                moneda1 = "USD";
                moneda2 = "COP";
                break;
            }
            case 6:{
                moneda1 = "COP";
                moneda2 = "USD";
                break;
            }
            default: break;
        }
        return "https://v6.exchangerate-api.com/v6/"+API_KEY+"/pair/"+moneda1+"/"+moneda2;
    }

}