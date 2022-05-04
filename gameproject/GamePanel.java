/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.awt.event.KeyListener;

//import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javax.swing.JPanel;



public class GamePanel extends JPanel implements ActionListener{
static final int SCREEN_WIDTH=600;
static final int SCREEN_HEIGHT=600;
static final int UNIT_SIZE =25;
static final int GAME_UNITS=((SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE);
static final int DELAY=325;
int x[]=new int[GAME_UNITS]; 
 int y[]=new int[GAME_UNITS];
 int eggX[]=new int[GAME_UNITS]; 
 int eggY[]=new int[GAME_UNITS];

int catNumber=16;
int point;
int catX[]=new int[GAME_UNITS];
int catY[]=new int[GAME_UNITS];
int target1[]=new int[GAME_UNITS];
int target2[]=new int[GAME_UNITS];
char eggDirection[]=new char[GAME_UNITS];
int number;
int egg;

char direction='M';//u UP,R RIGHT,D DOWN,L LEFT 
boolean running =false ;
int u=0,d=0;
Timer timer;
Random random;
JButton button;


GamePanel(){
random =new Random();
this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
this.setBackground(Color.black);
this.setFocusable(true);
this.addKeyListener(new MyKeyAdapter());
button=new JButton();    
button.setBounds(200,100,300,50);
button.addActionListener(this);
button.setText("NEXT LEVEL");
button.setFocusable(false);
startGame();

}//the end of constructor

public void startGame(){
newCats(); 
newTargets();
egg=0;
running=true;
timer = new Timer(DELAY,this);
timer.start();
}


@Override
public void paintComponent(Graphics g){
super.paintComponent(g);
draw(g);


}



public void draw(Graphics g){
if(running){
int i;
//draw squares in the frame
for( i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){

g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
}
//******draw  the cats
 i=0;
ImageIcon image1=new ImageIcon("C:\\Users\\seyma\\OneDrive\\Belgeler\\NetBeansProjects\\GameProject\\src\\gameproject\\cat3.jpg");

for(;i<catNumber;i++){
image1.paintIcon(this, g, catX[i], catY[i]);
}

//draw golden egg

ImageIcon image2=new ImageIcon("C:\\Users\\seyma\\OneDrive\\Belgeler\\NetBeansProjects\\GameProject\\src\\gameproject\\Goldegg.jpg");
for(i=0;i<25;i++){
image2.paintIcon(this, g, eggX[i], eggY[i]);
}


//*****draw the chicken

ImageIcon image3=new ImageIcon("C:\\Users\\seyma\\OneDrive\\Belgeler\\NetBeansProjects\\GameProject\\src\\gameproject\\chicken4.jpg");
image3.paintIcon(this, g, x[0], y[0]);

//*****draw targets
for(i=0;i<13;i++){
g.setColor(Color.red);


if(i<3){ g.fillOval(0,target1[i],10,10);}
else if(i>=3&&i<6){g.fillOval(0,target1[i],15,20);}
else{g.fillOval(0,target1[i],25,25);}


if(i<3){ g.fillOval(575,target2[i],10,10);}
else if(i>=3&&i<6){g.fillOval(575,target2[i],15,20);}
else{g.fillOval(575,target2[i],25,25);}

}

//print the score 
g.setColor(Color.red);
g.setFont(new Font("Ink Free",Font.BOLD,50));
FontMetrics metrics=getFontMetrics(g.getFont());
g.drawString("Score: "+point,(SCREEN_WIDTH-metrics.stringWidth("Score: "+point))/2,SCREEN_HEIGHT/2);


}//if->running

else{
gameOver(g);
}

}//the end of draw func

public void newCats(){
    int j=0;
    for(;j<catNumber;j++){
catX[j] = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
if(catX[j]==0){catX[j]+=25;}
else if(catX[j]==575){catX[j]-=25;}

catY[j] = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    
if(catY[j]==0){catX[j]+=50;}
    }
}
public void newTargets(){
    int j=0;
    for(;j<10;j++){
target1[j] = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
if(target1[j] ==0){target1[j] =+UNIT_SIZE;}
target2[j] = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
}

public void moveEgg(){

    switch(direction){
    case 'U':
eggX[egg]+=UNIT_SIZE;
eggDirection[egg]='u';egg++;
direction='M';
break;

case 'D':
eggX[egg]-=UNIT_SIZE;
eggDirection[egg]='d';egg++;
direction='M';
break;

    }

}




public void moveChicken(){
number=0;
switch(direction){
case 'M':    
y[0]+=UNIT_SIZE;
for(;number<25;number++){

if(eggDirection[number]=='u'){eggX[number]+=UNIT_SIZE;}
else if(eggDirection[number]=='d'){eggX[number]-=UNIT_SIZE;}
else{ eggY[number]+=UNIT_SIZE;}

}

break;


case 'L':
x[0]-=UNIT_SIZE;    

for(;number<25;number++){

if(eggDirection[number]=='u'){eggX[number]+=UNIT_SIZE;}
else if(eggDirection[number]=='d'){eggX[number]-=UNIT_SIZE;}
else{ eggX[number]-=UNIT_SIZE;}

}

direction='M';
break;

case 'R':
x[0]+=UNIT_SIZE; 

for(;number<25;number++){

if(eggDirection[number]=='u'){eggX[number]+=UNIT_SIZE;}
else if(eggDirection[number]=='d'){eggX[number]-=UNIT_SIZE;}
else{ eggX[number]+=UNIT_SIZE;}

}

direction='M';
break;
}//switch-case


}

public boolean checkCat(){
    int k=0;
    for(;k<catNumber;k++){
if((x[0]==catX[k])&&(y[0]==catY[k])){
return true;}
    }
    return false;
}


public void checkCollisions(){
//check if head collides with body

if(checkCat())
running=false;


//check if head touches left border
if(x[0]<0){running=false;}
//check if head touches right border
if(x[0]>SCREEN_WIDTH){running=false;}

//check if head touches TOP border
if(y[0]<0){running=false;}
//check if head touches BOTTOM border
if(y[0]>SCREEN_HEIGHT){
this.add(button);
 button.setVisible(true);
}

if(!running){timer.stop();}

}

public void checkTargets(){
    int f1,f2;

for(f1=0;f1<egg;f1++){
for(f2=0;f2<13;f2++){
if(target1[f2]==eggY[f1]&&eggX[f1]==0){
    if(f2<3){ point+=10;}
    else if(f2>=3&&f2<6){point+=15;}
    else{point+=20;}
    
   }
if(target2[f2]==eggY[f1]&&eggX[f1]==575){
 if(f2<3){ point+=10;}
    else if(f2>=3&&f2<6){point+=15;}
    else{point+=20;}

}
}
}
}

public void gameOver(Graphics g){
//print score
g.setColor(Color.red);
g.setFont(new Font("Ink Free",Font.BOLD,50));
FontMetrics metrics1=getFontMetrics(g.getFont());
g.drawString("Score: "+point,(SCREEN_WIDTH-metrics1.stringWidth("Score: "+point))/2,g.getFont().getSize());


//print gameOver
g.setColor(Color.red);
g.setFont(new Font("Ink Free",Font.BOLD,75));
FontMetrics metrics2=getFontMetrics(g.getFont());
g.drawString("***GAME OVER***",(SCREEN_WIDTH-metrics2.stringWidth("***GAME OVER***"))/2,SCREEN_HEIGHT/2);
}




@Override
public void actionPerformed(ActionEvent e){
    int t=0;

  
if(running){

moveEgg();  
moveChicken();

checkTargets();
checkCollisions();
        if(e.getSource()==button){
             button.setVisible(false);
   x[0]=0;
  y[0]=0;
  catNumber+=10;
  
  for(;t<25;t++){eggDirection[t]='A';}
  for(t=0;t<25;t++){
eggX[t]=eggY[t]=0;
}

 startGame();
      }
    
}
repaint();

}



public class MyKeyAdapter extends KeyAdapter{

@Override
public void keyPressed(KeyEvent e){
   
   
switch(e.getKeyCode()){

case KeyEvent.VK_LEFT:
direction='L';
break;

case KeyEvent.VK_RIGHT:
direction='R';
break;

case KeyEvent.VK_UP:
direction='U';
break;

case KeyEvent.VK_DOWN:
direction='D';
break;


}//switch-case



}//keyPressed
}




}//the end of class
