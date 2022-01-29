package com.company;

import com.company.connect.DataConnection;
import com.company.models.Ball;
import com.company.models.BallsArray;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ObjectContainer db = DataConnection.getInstance();
        BallsArray balls = new BallsArray();
        int option=0;
        while(option!=5){
            option=0;
            System.out.println("BALL CRUD");
            System.out.println("1.Leer lista de pelotas guardadas");
            System.out.println("2.Añadir Pelota");
            System.out.println("3.Borrar Pelota");
            System.out.println("4.Editar Pelota");
            System.out.println("5.Salir");
            try{
                option=in.nextInt();
            }catch (Exception e) {
                e.printStackTrace();
                option = 0;
            }
            switch (option){
                case 1:
                    System.out.println(balls.getBalls().toString());
                    break;

                case 2:
                    Ball ball = new Ball();
                    System.out.println("Introduce el numero de identificación (Números enteros):");
                    ball.id=in.nextInt();
                    System.out.println("Introduce tamaño en centimetros:");
                    ball.size=in.nextFloat();
                    System.out.println("Introduce color:");
                    ball.color=in.next();
                    System.out.println("Introduce peso en gramos:");
                    ball.weight=in.nextFloat();
                    System.out.println(balls.addBall(ball));


                    break;

                case 3:

                    System.out.println(balls.getBalls().toString());
                    System.out.println("Selecciona el id de la pelota que quieres eliminar");
                    int id=in.nextInt();
                    int result=balls.deleteBall(balls.getBallbyId(id));
                    System.out.println("Se han eliminado "+result+" pelotas");
                    break;

                case 4:
                    Ball ball2 = new Ball();
                    System.out.println(balls.getBalls().toString());
                    System.out.println("Selecciona el id de la pelota que quieres editar");
                    int id2=in.nextInt();
                    System.out.println(balls.getBallbyId(id2));
                    System.out.println("Introduce tamaño en centimetros:");
                    ball2.size=in.nextFloat();
                    System.out.println("Introduce color:");
                    ball2.color=in.next();
                    System.out.println("Introduce peso en gramos:");
                    ball2.weight=in.nextFloat();
                    balls.updateBall(ball2);
                    System.out.println("Se ha editado con éxito la pelota: ");
                    break;
                case 5:
                    System.out.println("¡Adios!");
                    break;

                default:
                    System.out.println("Seleccionada opcion "+option);
                    System.out.println("Introduzca una cantidad válida");



            }
        }



    }
}
