package org.challenge;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.challenge.Modelo.Moneda;
import org.challenge.Modelo.MonedaDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static final String API_KEY = "2278893e8dc8a69b635e0c81";
    public static void main(String[] args) {
        /*Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(persona);*/
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);
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
                        //System.out.println("url: " + url);
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .GET()
                                .build();
                        HttpResponse<String> response = client
                                .send(request, HttpResponse.BodyHandlers.ofString());

                        String json = response.body();

                        Gson gson = new GsonBuilder()
                                .create();

                        MonedaDTO monedaDTO = gson.fromJson(json, MonedaDTO.class);
                        Moneda moneda = new Moneda(monedaDTO);
                        System.out.println(moneda.toString());
                        ImprimirResultado(in, moneda);
                    }
                } else {
                    System.out.println("Opción no disponible, vuelva a intentarlo\n");
                    op = 1;
                }
            }catch(InputMismatchException e){
                System.out.println("Ingrese un numero válido");
                in.nextLine();
                op = 1;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally{
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

    static void ImprimirResultado(Scanner in, Moneda moneda){
        boolean valido = true;
        while(valido){
            try{
                double cash;
                System.out.print("Ingresa la cantidad de dinero a convertir: ");
                cash = in.nextDouble();
                if((cash <= 0))
                    System.out.println("Ingrese un numero positivo y mayor que 0");
                else {
                    System.out.printf(Locale.US, "%.2f %s equivale a %.2f %s",
                            cash, moneda.getBaseCode(), moneda.getConversionRate()*cash, moneda.getTargetCode());
                    valido = false;
                }
            }catch(RuntimeException e){
                System.out.println("Ingrese un valor numérico");
                in.nextLine();
            }

        }
    }

}