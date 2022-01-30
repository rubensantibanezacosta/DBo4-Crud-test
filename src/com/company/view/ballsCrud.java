package com.company.view;

import com.company.models.Ball;
import com.company.models.BallsArray;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Type;
import java.util.List;

public class ballsCrud {
    private static String state = "add";
    private JPanel panel1;
    private JTextField textId;
    private JTextField textSize;
    private JTextField textColor;
    private JTextField textWeight;
    private JButton guardarButton;
    private JButton borrarButton;
    private JTable table1;
    private  BallsArray ballsArray=new BallsArray();
    private FocusListener rowselect;


    public ballsCrud() {

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (textId.getText().equals("") || textSize.getText().equals("") || textColor.getText().equals("")|| textWeight.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Rellena los datos que faltan");

                }else{
                    Ball createdBall = new Ball(Integer.parseInt(textId.getText()), Float.parseFloat(textSize.getText()), textColor.getText(), Float.parseFloat(textWeight.getText()));
                    ballsArray.addBall(createdBall);
                    createTable();
                    textId.setText("");
                    textSize.setText("");
                    textColor.setText("");
                    textWeight.setText("");
                }
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textId.getText().equals("")){
                   JOptionPane.showMessageDialog(null, "Rellene los datos");
                }else {
                    ballsArray.deleteBall(Integer.parseInt(textId.getText()));
                    createTable();
                    textId.setText("");
                    textSize.setText("");
                    textColor.setText("");
                    textWeight.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        ballsCrud crud = new ballsCrud();
        JFrame frame = new JFrame("ballsCrud");
        frame.setContentPane(crud.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        crud.createTable();
    }

public void createTable(){

        List<Ball> dataList =  ballsArray.getBalls();
        final Object [][] data = new Object [dataList.size()][4];
    for (int i = 0; i <dataList.size() ; i++) {
            Object[] object = {dataList.get(i).getId(),dataList.get(i).getSize(), dataList.get(i).getColor(),dataList.get(i).getWeight()};
            data[i][0]=object[0];data[i][1]=object[1];data[i][2]=object[2];data[i][3]=object[3];
        }
        table1.setModel(new DefaultTableModel(

                data,
                new String[]{"id", "tamaño","color","peso"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return  false;
            }
        });

    table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selectedRow=-1;

          try {
              selectedRow = Integer.parseInt(String.valueOf(e.getSource().toString().charAt((e.getSource().toString().length()) - 2)));

              List<Ball> balls = ballsArray.getBalls();
              table1.doLayout();
              textId.requestFocus();


              textId.setText(String.valueOf(ballsArray.getBalls().get(selectedRow).getId()));
              textSize.setText(String.valueOf(ballsArray.getBalls().get(selectedRow).getSize()));
              textColor.setText(ballsArray.getBalls().get(selectedRow).getColor());
              textWeight.setText(String.valueOf(ballsArray.getBalls().get(selectedRow).getWeight()));
          }catch (Exception ex){

          }
        createTable();
        }

    });

}

}
