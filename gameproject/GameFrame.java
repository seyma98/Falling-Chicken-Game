/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

import javax.swing.*;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

GameFrame(){

//GamePanel panel=new GamePanel();


this.add(new GamePanel());//it is the same with the top of this line
this.setTitle("***FLYING CHICKEN GAME***");
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setResizable(false);



this.pack();
this.setVisible(true);
this.setLocationRelativeTo(null);

}//end of constructor



}//the end of the class
