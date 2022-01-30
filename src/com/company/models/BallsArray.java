package com.company.models;

import com.company.connect.DataConnection;
import com.db4o.ObjectContainer;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class BallsArray {
    ObjectContainer db = DataConnection.getInstance();
    public List<Ball> balls = new ArrayList<>();

    public BallsArray() {
    }

    public List<Ball> getBalls() {
        this.balls=db.query(Ball.class);
        return balls;
    }



    public String addBall(Ball ball) {

        if((db.queryByExample(new Ball(ball.getId())).size()>0)){
            List<Ball> existingBalls=db.queryByExample(new Ball(ball.getId()));
            db.delete(existingBalls.get(0));
            db.store(ball);
            return "Guardado con éxito";
        }else{

            db.store(ball);
            return "Ya existe esa pelota";
        }
    }

    public void setBalls(List<Ball> balls) {
        this.balls = balls;
    }

    public int updateBall(Ball ball) {
        this.balls=db.queryByExample(ball);
        if(balls.size()>0){
            db.delete(ball);
            db.commit();
            db.store(ball);
            db.commit();
            return 1;
        }

        return 0;
    }

    public int getLenght() {
        return this.balls.size();
    }


    public Ball getBallbyId(int i) {
        return (Ball) db.queryByExample(new Ball(i)).get(0);
    }

    public String deleteBall(int id){
        if((db.queryByExample(new Ball(id)).size()>0)){
            List<Ball> existingBalls=db.queryByExample(new Ball(id));
            db.delete(existingBalls.get(0));
            return "Guardado con éxito";
        }else{


            return "Ya existe esa pelota";
        }

        }



    @Override
    public String toString() {
        return "BallsArray{" +
                "balls=" + balls +
                '}';
    }
}
