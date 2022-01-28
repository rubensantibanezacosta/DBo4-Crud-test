package com.company;

import com.company.connect.DataConnection;
import com.company.models.Ball;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Main {

    public static void main(String[] args) {
        ObjectContainer db = DataConnection.getInstance();
        Ball balltest = new Ball(10,0,"test",0);
        db.store(balltest);
        ObjectSet<Ball> balls = db.query(Ball.class);
        System.out.println(balls.size());
    }
}
