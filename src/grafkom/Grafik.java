/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafkom;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author gusman
 */
public class Grafik extends JPanel {
}

class Titik extends JPanel{
    int x, y;
    Color color = Color.BLACK;

    public Titik() {
        setBackground(Color.WHITE);        
        setSize(1100, 750);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        g.drawLine(x, y, x, y); 
    }
}

/*
 * MEMBUAT GARIS METODE BRESENHAM
 */
class Garis extends JPanel {
    int x0, y0, x1, y1;
    Color color = Color.BLACK;

    public Garis() {
        setBackground(Color.WHITE);        
        setSize(1100, 750);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        
        int x_start, y_start;
        int x_end, y_end;
        
        if(x0 < x1) {
            x_start = x0;
            y_start = y0;
            x_end = x1;
            y_end = y1;
        }
        else {
            x_start = x1;
            y_start = y1;
            x_end = x0;
            y_end = y0;
        }

        if(x_start == x_end) { // vertical line
            int bottom, top;
            if(y_start > y_end) {
                bottom = y_end;
                top = y_start;
            }
            else {
                bottom = y_start;
                top = y_end;
            }
            for(int i = bottom; i <= top; i++)
            	g.drawLine(x_start, i,x_start, i);
        }

        else if(y_start == y_end) { // horizontal line
            int left, right;
            if(x_start > x_end) {
                left = x_end;
                right = x_start;
            }
            else {
                left = x_start;
                right = x_end;
            }
            for(int i = left; i <= right; i++)
            	g.drawLine(i, y_start,i, y_start);
        }
        
        else { 

            int dx = x_end - x_start;
            int dy = y_end - y_start;
            
            int reflection = 1; 

            if(dy < 0) { // negative slope
            	
                y_start*= -1;
                y_end*= -1;
                dy*= -1;

                reflection = -1;
            }
 

            int x = x_start;
            int y = y_start;
 
            if(dy > dx) { 
            	
               int two_dx = 2 * dx;
               int two_dxdy = 2 * (dx - dy);
               int p = two_dx - dy;

               g.drawLine(x, (y * reflection), x, (y * reflection));
               while(y < y_end) {
                   y++;
                   if(p <= 0)
                       p+= two_dx;
                   else {
                       x++;
                       p+= two_dxdy;
                   }
                   g.drawLine(x, (y * reflection), x, (y * reflection));
               }               
           }
           
           else { 
               int two_dy = 2 * dy;
               int two_dydx = 2 * (dy - dx);               
               int p = two_dy - dx;    
               
               g.drawLine(x, (y * reflection), x, (y * reflection));
               while(x < x_end) {
                   x++;
                   if(p <= 0)
                       p+= two_dy;
                   else {
                       y++;
                       p+= two_dydx;
                   }
                   g.drawLine(x, (y * reflection), x, (y * reflection));
               }
           }
           
        } // general line
        
    }
}

class GarisDDA extends JPanel {
    int x0, y0, x1, y1;
    Color color = Color.BLACK;

    public GarisDDA() {
        setBackground(Color.WHITE);        
        setSize(1100, 750);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);     

        int xdiff = x0 - x1; 
        int ydiff = y0 - y0; 
        int slope  = 1; 
        if ( y0  == y1  ) // horizontal
        {
            slope = 0;
        }
        else if (  x0 == x1 ) // vertical
        {
            slope = 2;
        }
        else
        {
            slope = xdiff/ydiff; 
        }

        if ( slope <= 1 )
        {    
            int startx = 0;
            int endx   = 0;
            
            if ( x0 > x1 )
            {
                startx = x1;
                endx   = x0;
            }
            else 
            {
                startx = x0;
                endx   = x1;
            }

            float y = y0;
            for(int x = startx; x <= endx; x++)
            {
                y += slope;
                g.drawLine(x, (int)y, x, (int)y);
            }
        }

        else if ( slope > 1 )
        {
            float x = x0; // initial value
            for(int y = y0;y <= y1; y++)
            {
                x += 1/slope;
                g.drawLine((int)x, y, (int)x, y);
            }

        }
        
    }
    
}
/*
 * LINGKARAN METODE MID POINT
 */
class Lingkaran extends JPanel {
    int xCenter, yCenter, radius;
    Color color = Color.BLACK;
    
    public Lingkaran() {
        setBackground(Color.WHITE);        
        setSize(1100, 750);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        
        int p,x,y;
        p=x=y=0;

        y = radius;
        
        // gambar titik awal
        g.drawLine( xCenter + x, yCenter + y, xCenter + x, yCenter + y);
        g.drawLine( xCenter - x, yCenter + y, xCenter - x, yCenter + y);
        g.drawLine( xCenter + x, yCenter - y, xCenter + x, yCenter - y);
        g.drawLine( xCenter - x, yCenter - y, xCenter - x, yCenter - y);
        g.drawLine( xCenter + y, yCenter + x, xCenter + y, yCenter + x);
        g.drawLine( xCenter - y, yCenter + x, xCenter - y, yCenter + x);
        g.drawLine( xCenter + y, yCenter - x, xCenter + y, yCenter - x);
        g.drawLine( xCenter - y, yCenter - x, xCenter - y, yCenter - x);

        p = 1 - radius;
        while (x < y){
            if (p < 0){
                x = x+1;
            }
            else{
                x = x+1;
                y = y-1;
            }

            if (p < 0){
                p = p+2*x+1;
            }
            else{
                p = p+2*(x-y)+1;
            }

            g.drawLine(xCenter + x, yCenter + y,xCenter + x, yCenter + y);
            g.drawLine(xCenter - x, yCenter + y,xCenter - x, yCenter + y);
            g.drawLine(xCenter + x, yCenter - y,xCenter + x, yCenter - y);
            g.drawLine(xCenter - x, yCenter - y, xCenter - x, yCenter - y);
            g.drawLine(xCenter + y, yCenter + x, xCenter + y, yCenter + x);
            g.drawLine(xCenter - y, yCenter + x,xCenter - y, yCenter + x);
            g.drawLine(xCenter + y, yCenter - x,xCenter + y, yCenter - x);
            g.drawLine(xCenter - y, yCenter - x,xCenter - y, yCenter - x);
        }
         
    }
    
}

/*
 * ELIPS METODE MID POINT
 */
class Elips extends JPanel {
    int Rx, Ry, X1, Y1;
    
    Color color = Color.BLACK;
    
    public Elips() {
        setBackground(Color.WHITE);        
        setSize(1100, 750);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);        
        
         int Cx = 0;
         int Cy = 0;
         int x = 0;
         int y = Ry;
                 
        
        g.drawLine( Cx + x, Cy + y, Cx + x, Cy + y);
        g.drawLine( Cx - x, Cy + y, Cx - x, Cy + y);
        g.drawLine( Cx + x, Cy - y, Cx + x, Cy - y);
        g.drawLine( Cx - x, Cy - y, Cx - x, Cy - y);
        g.drawLine( Cx + y, Cy + x, Cx + y, Cy + x);
        g.drawLine( Cx - y, Cy + x, Cx - y, Cy + x);
        g.drawLine( Cx + y, Cy - x, Cx + y, Cy - x);
        g.drawLine( Cx - y, Cy - x, Cx - y, Cy - x);
        
         int Rx2 = Rx * Rx;
         int Ry2 = Ry * Ry;
         int twoRx2 = 2 * Rx2;
         int twoRy2 = 2 * Ry2;
         x = 0;
         double p = (Ry2 - (Rx2 * Ry) + (0.25 + Rx2));
         int px = 0;
         int py = twoRx2 * y;

         //Region 1
         while (px < py)
         {
             x = x + 1;
             px = twoRy2 + px;
             if (p < 0)
             {
                 p = Ry2 + px + p;
             }
             else 
             {
                 y = y - 1;
                 py = py - twoRx2;
                 p = Ry2 + px - py + p;
             }
                   
        
            g.drawLine(x+X1, y+Y1, x+X1, y+Y1);
            g.drawLine(x*(-1)+X1, y+Y1, x*(-1)+X1, y+Y1);
            g.drawLine(x*(-1)+X1, y*(-1)+Y1, x*(-1)+X1, y*(-1)+Y1);
            g.drawLine(x+X1, y*(-1)+Y1, x+X1, y*(-1)+Y1);            
            g.drawLine(x+Y1, y+X1, x+Y1, y+X1);
            g.drawLine(x*(-1)+Y1, y+X1, x*(-1)+Y1, y+X1);
            g.drawLine(x*(-1)+Y1, y*(-1)+X1, x*(-1)+Y1, y*(-1)+X1);
            g.drawLine(x+Y1, y*(-1)+X1, x+Y1, y*(-1)+X1);
        
         }

         //Region2 
         p = (Ry2 * (x + 0.5) * (x + 0.5) + Rx2 * (y - 1) * (y - 1) - Rx2 * Ry2);
         while (y > 0)
         {
             y = y - 1;
             py = py - twoRx2;
             if (p > 0)
             {
                 p = Rx2 - py + p;
             }
             else
             {
                 x = x + 1;
                 px = twoRy2 + px;
                 p = Rx2 + px - py + p;
             }
            
            g.drawLine(x+X1, y+Y1, x+X1, y+Y1);
            g.drawLine(x*(-1)+X1, y+Y1, x*(-1)+X1, y+Y1);
            g.drawLine(x*(-1)+X1, y*(-1)+Y1, x*(-1)+X1, y*(-1)+Y1);
            g.drawLine(x+X1, y*(-1)+Y1, x+X1, y*(-1)+Y1);            
            g.drawLine(x+Y1, y+X1, x+Y1, y+X1);
            g.drawLine(x*(-1)+Y1, y+X1, x*(-1)+Y1, y+X1);
            g.drawLine(x*(-1)+Y1, y*(-1)+X1, x*(-1)+Y1, y*(-1)+X1);
            g.drawLine(x+Y1, y*(-1)+X1, x+Y1, y*(-1)+X1);
         }    

    }
    
}

class NPM extends JPanel{
    int x, y;
    Color color = Color.BLACK;

    public NPM() {
        setBackground(Color.WHITE);        
        setSize(1100, 750);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        
//        tebal 40
        
        //Membuat G
        g.drawLine(100+x, 100+y, 220+x, 100+y);
        g.drawLine(140+x, 140+y, 220+x, 140+y);
        g.drawLine(160+x, 180+y, 220+x, 180+y);
        g.drawLine(160+x, 220+y, 180+x, 220+y);
        g.drawLine(140+x, 260+y, 180+x, 260+y);
        g.drawLine(100+x, 300+y, 220+x, 300+y);        

        g.drawLine(100+x, 100+y, 100+x, 300+y);
        g.drawLine(140+x, 140+y, 140+x, 260+y);
        g.drawLine(160+x, 180+y, 160+x, 220+y);
        g.drawLine(180+x, 220+y, 180+x, 260+y);
        g.drawLine(220+x, 180+y, 220+x, 300+y);
        g.drawLine(220+x, 100+y, 220+x, 140+y);
        
        //Membuat 5
        g.drawLine(240+x, 100+y, 360+x, 100+y);
        g.drawLine(280+x, 140+y, 360+x, 140+y);
        g.drawLine(280+x, 180+y, 360+x, 180+y);
        g.drawLine(240+x, 220+y, 320+x, 220+y);
        g.drawLine(240+x, 260+y, 320+x, 260+y);
        g.drawLine(240+x, 300+y, 360+x, 300+y);        

        g.drawLine(240+x, 100+y, 240+x, 220+y);
        g.drawLine(240+x, 260+y, 240+x, 300+y);
        g.drawLine(280+x, 140+y, 280+x, 180+y);
        g.drawLine(320+x, 220+y, 320+x, 260+y);
        g.drawLine(360+x, 100+y, 360+x, 140+y);
        g.drawLine(360+x, 180+y, 360+x, 300+y);
        
        //Membuat 7
        g.drawLine(380+x, 100+y, 500+x, 100+y);
        g.drawLine(380+x, 140+y, 460+x, 140+y);
        g.drawLine(460+x, 300+y, 500+x, 300+y);        

        g.drawLine(380+x, 100+y, 380+x, 140+y);
        g.drawLine(460+x, 140+y, 460+x, 300+y);
        g.drawLine(500+x, 100+y, 500+x, 300+y);

        //Membuat 3
        g.drawLine(520+x, 100+y, 640+x, 100+y);
        g.drawLine(520+x, 140+y, 600+x, 140+y);
        g.drawLine(520+x, 180+y, 600+x, 180+y);
        g.drawLine(520+x, 220+y, 600+x, 220+y);
        g.drawLine(520+x, 260+y, 600+x, 260+y);
        g.drawLine(520+x, 300+y, 640+x, 300+y);        

        g.drawLine(520+x, 100+y, 520+x, 140+y);
        g.drawLine(520+x, 180+y, 520+x, 220+y);
        g.drawLine(520+x, 260+y, 520+x, 300+y);
        g.drawLine(600+x, 140+y, 600+x, 180+y);
        g.drawLine(600+x, 220+y, 600+x, 260+y);
        g.drawLine(640+x, 100+y, 640+x, 300+y);

        //Membuat 8
        g.drawLine(660+x, 100+y, 780+x, 100+y);
        g.drawLine(700+x, 140+y, 740+x, 140+y);
        g.drawLine(700+x, 180+y, 740+x, 180+y);
        g.drawLine(700+x, 220+y, 740+x, 220+y);
        g.drawLine(700+x, 260+y, 740+x, 260+y);
        g.drawLine(660+x, 300+y, 780+x, 300+y);        

        g.drawLine(660+x, 100+y, 660+x, 300+y);
        g.drawLine(700+x, 140+y, 700+x, 180+y);
        g.drawLine(700+x, 220+y, 700+x, 260+y);
        g.drawLine(740+x, 140+y, 740+x, 180+y);
        g.drawLine(740+x, 220+y, 740+x, 260+y);
        g.drawLine(780+x, 100+y, 780+x, 300+y);

        //Membuat o
        g.drawLine(800+x, 100+y, 920+x, 100+y);
        g.drawLine(840+x, 140+y, 880+x, 140+y);
        g.drawLine(840+x, 260+y, 880+x, 260+y);
        g.drawLine(800+x, 300+y, 920+x, 300+y);        

        g.drawLine(800+x, 100+y, 800+x, 300+y);
        g.drawLine(840+x, 140+y, 840+x, 260+y);
        g.drawLine(880+x, 140+y, 880+x, 260+y);
        g.drawLine(920+x, 100+y, 920+x, 300+y);
    }
}

class NPMScale extends JPanel{
    int x, y;
    Color color = Color.BLACK;

    public NPMScale() {
        setBackground(Color.WHITE);        
        setSize(1100, 750);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        
//        tebal 40
        
        //Membuat G
        g.drawLine(100*x, 100*y, 220*x, 100*y);
        g.drawLine(140*x, 140*y, 220*x, 140*y);
        g.drawLine(160*x, 180*y, 220*x, 180*y);
        g.drawLine(160*x, 220*y, 180*x, 220*y);
        g.drawLine(140*x, 260*y, 180*x, 260*y);
        g.drawLine(100*x, 300*y, 220*x, 300*y);        

        g.drawLine(100*x, 100*y, 100*x, 300*y);
        g.drawLine(140*x, 140*y, 140*x, 260*y);
        g.drawLine(160*x, 180*y, 160*x, 220*y);
        g.drawLine(180*x, 220*y, 180*x, 260*y);
        g.drawLine(220*x, 180*y, 220*x, 300*y);
        g.drawLine(220*x, 100*y, 220*x, 140*y);
        
        //Membuat 5
        g.drawLine(240*x, 100*y, 360*x, 100*y);
        g.drawLine(280*x, 140*y, 360*x, 140*y);
        g.drawLine(280*x, 180*y, 360*x, 180*y);
        g.drawLine(240*x, 220*y, 320*x, 220*y);
        g.drawLine(240*x, 260*y, 320*x, 260*y);
        g.drawLine(240*x, 300*y, 360*x, 300*y);        

        g.drawLine(240*x, 100*y, 240*x, 220*y);
        g.drawLine(240*x, 260*y, 240*x, 300*y);
        g.drawLine(280*x, 140*y, 280*x, 180*y);
        g.drawLine(320*x, 220*y, 320*x, 260*y);
        g.drawLine(360*x, 100*y, 360*x, 140*y);
        g.drawLine(360*x, 180*y, 360*x, 300*y);
        
        //Membuat 7
        g.drawLine(380*x, 100*y, 500*x, 100*y);
        g.drawLine(380*x, 140*y, 460*x, 140*y);
        g.drawLine(460*x, 300*y, 500*x, 300*y);        

        g.drawLine(380*x, 100*y, 380*x, 140*y);
        g.drawLine(460*x, 140*y, 460*x, 300*y);
        g.drawLine(500*x, 100*y, 500*x, 300*y);

        //Membuat 3
        g.drawLine(520*x, 100*y, 640*x, 100*y);
        g.drawLine(520*x, 140*y, 600*x, 140*y);
        g.drawLine(520*x, 180*y, 600*x, 180*y);
        g.drawLine(520*x, 220*y, 600*x, 220*y);
        g.drawLine(520*x, 260*y, 600*x, 260*y);
        g.drawLine(520*x, 300*y, 640*x, 300*y);        

        g.drawLine(520*x, 100*y, 520*x, 140*y);
        g.drawLine(520*x, 180*y, 520*x, 220*y);
        g.drawLine(520*x, 260*y, 520*x, 300*y);
        g.drawLine(600*x, 140*y, 600*x, 180*y);
        g.drawLine(600*x, 220*y, 600*x, 260*y);
        g.drawLine(640*x, 100*y, 640*x, 300*y);

        //Membuat 8
        g.drawLine(660*x, 100*y, 780*x, 100*y);
        g.drawLine(700*x, 140*y, 740*x, 140*y);
        g.drawLine(700*x, 180*y, 740*x, 180*y);
        g.drawLine(700*x, 220*y, 740*x, 220*y);
        g.drawLine(700*x, 260*y, 740*x, 260*y);
        g.drawLine(660*x, 300*y, 780*x, 300*y);        

        g.drawLine(660*x, 100*y, 660*x, 300*y);
        g.drawLine(700*x, 140*y, 700*x, 180*y);
        g.drawLine(700*x, 220*y, 700*x, 260*y);
        g.drawLine(740*x, 140*y, 740*x, 180*y);
        g.drawLine(740*x, 220*y, 740*x, 260*y);
        g.drawLine(780*x, 100*y, 780*x, 300*y);

        //Membuat o
        g.drawLine(800*x, 100*y, 920*x, 100*y);
        g.drawLine(840*x, 140*y, 880*x, 140*y);
        g.drawLine(840*x, 260*y, 880*x, 260*y);
        g.drawLine(800*x, 300*y, 920*x, 300*y);        

        g.drawLine(800*x, 100*y, 800*x, 300*y);
        g.drawLine(840*x, 140*y, 840*x, 260*y);
        g.drawLine(880*x, 140*y, 880*x, 260*y);
        g.drawLine(920*x, 100*y, 920*x, 300*y);
    }
}

class NPMRotate extends JPanel{
    int r;
    Color color = Color.BLACK;

    public NPMRotate() {
        setBackground(Color.WHITE);        
        setSize(1100, 750);
    }
    
    public int x1(int x, int y) {
        
//        int xp = 100 + (920 - 100 / 2);
//        int yp = 100 + (300 - 100 / 2);
    	int xp = 100;
    	int yp = 150;

        int x1= (int) (xp + ((x - xp) * Math.cos(r)) - ((y - yp) * Math.sin(r)));
//        int x1= (int) (xp + x - xp * Math.cos(r) - (y - yp * Math.sin(r)));
//        double y1= (yp + ((x - xp) * Math.sin(r)) + ((y - yp) * Math.cos(r)));
    	return x1;
    }   
    
    public int y1(int x, int y) {
        
//        int xp = 100 + (920 - 100 / 2);
//        int yp = 100 + (300 - 100 / 2);
    	int xp = 100;
    	int yp = 150;

//        double x1= (xp + ((x - xp) * Math.cos(r)) - ((y - yp) * Math.sin(r)));
        int y1 = (int) (yp + ((x - xp) * Math.sin(r)) + ((y - yp) * Math.cos(r)));
//        int y1 = (int) (yp + x - xp * Math.sin(r) + (y - yp * Math.cos(r)));
    	return y1;
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);        
        
//        int xp = 100 + (920 - 100 / 2);
//        int yp = 100 + (300 - 100 / 2);

//        x1= (xp + ((X - xp) * Math.cos(r)) - ((Y - yp) * Math.sin(r)))
//        y1= (yp + ((X - xp) * Math.sin(r)) + ((Y - yp) * Math.cos(r)))
        
//        tebal 40
        
        //Membuat G
        g.drawLine(x1(100, 100), y1(100, 100), x1(220, 100), y1(220, 100));
        g.drawLine(x1(140, 140), y1(140, 140), x1(220, 140), y1(220, 140));
        g.drawLine(x1(160, 180), y1(160, 180), x1(220, 180), y1(220, 180));
        g.drawLine(x1(160, 220), y1(160, 220), x1(180, 220), y1(180, 220));
        g.drawLine(x1(140, 260), y1(140, 260), x1(180, 260), y1(180, 260));
        g.drawLine(x1(100, 300), y1(100, 300), x1(220, 300), y1(220, 300));        

        g.drawLine(x1(100, 100), y1(100, 100), x1(100, 300), y1(100, 300));
        g.drawLine(x1(140, 140), y1(140, 140), x1(140, 260), y1(140, 260));
        g.drawLine(x1(160, 180), y1(160, 180), x1(160, 220), y1(160, 220));
        g.drawLine(x1(180, 220), y1(180, 220), x1(180, 260), y1(180, 260));
        g.drawLine(x1(220, 180), y1(220, 180), x1(220, 300), y1(220, 300));
        g.drawLine(x1(220, 100), y1(220, 100), x1(220, 140), y1(220, 140));
        
        //Membuat 5
        g.drawLine(x1(240, 100), y1(240, 100), x1(360, 100), y1(360, 100));
        g.drawLine(x1(280, 140), y1(280, 140), x1(360, 140), y1(360, 140));
        g.drawLine(x1(280, 180), y1(280, 180), x1(360, 180), y1(360, 180));
        g.drawLine(x1(240, 220), y1(240, 220), x1(320, 220), y1(320, 220));
        g.drawLine(x1(240, 260), y1(240, 260), x1(320, 260), y1(320, 260));
        g.drawLine(x1(240, 300), y1(240, 300), x1(360, 300), y1(360, 300));

        g.drawLine(x1(240, 100), y1(240, 100), x1(240, 220), y1(240, 220));
        g.drawLine(x1(240, 260), y1(240, 260), x1(240, 300), y1(240, 300));
        g.drawLine(x1(280, 140), y1(280, 140), x1(280, 180), y1(280, 180));
        g.drawLine(x1(320, 220), y1(320, 220), x1(320, 260), y1(320, 260));
        g.drawLine(x1(360, 100), y1(360, 100), x1(360, 140), y1(360, 140));
        g.drawLine(x1(360, 180), y1(360, 180), x1(360, 300), y1(360, 300));
        
        //Membuat 7
        g.drawLine(x1(380, 100), y1(380, 100), x1(500, 100), y1(500, 100));
        g.drawLine(x1(380, 140), y1(380, 140), x1(460, 140), y1(460, 140));
        g.drawLine(x1(460, 300), y1(460, 300), x1(500, 300), y1(500, 300));

        g.drawLine(x1(380, 100), y1(380, 100), x1(380, 140), y1(380, 140));
        g.drawLine(x1(460, 140), y1(460, 140), x1(460, 300), y1(460, 300));
        g.drawLine(x1(500, 100), y1(500, 100), x1(500, 300), y1(500, 300));

        //Membuat 3
        g.drawLine(x1(520, 100), y1(520, 100), x1(640, 100), y1(640, 100));
        g.drawLine(x1(520, 140), y1(520, 140), x1(600, 140), y1(600, 140));
        g.drawLine(x1(520, 180), y1(520, 180), x1(600, 180), y1(600, 180));
        g.drawLine(x1(520, 220), y1(520, 220), x1(600, 220), y1(600, 220));
        g.drawLine(x1(520, 260), y1(520, 260), x1(600, 260), y1(600, 260));
        g.drawLine(x1(520, 300), y1(520, 300), x1(640, 300), y1(640, 300));        

        g.drawLine(x1(520, 100), y1(520, 100), x1(520, 140), y1(520, 140));
        g.drawLine(x1(520, 180), y1(520, 180), x1(520, 220), y1(520, 220));
        g.drawLine(x1(520, 260), y1(520, 260), x1(520, 300), y1(520, 300));
        g.drawLine(x1(600, 140), y1(600, 140), x1(600, 180), y1(600, 180));
        g.drawLine(x1(600, 220), y1(600, 220), x1(600, 260), y1(600, 260));
        g.drawLine(x1(640, 100), y1(640, 100), x1(640, 300), y1(640, 300));

        //Membuat 8
        g.drawLine(x1(660, 100), y1(660, 100), x1(780, 100), y1(780, 100));
        g.drawLine(x1(700, 140), y1(700, 140), x1(740, 140), y1(740, 140));
        g.drawLine(x1(700, 180), y1(700, 180), x1(740, 180), y1(740, 180));
        g.drawLine(x1(700, 220), y1(700, 220), x1(740, 220), y1(740, 220));
        g.drawLine(x1(700, 260), y1(700, 260), x1(740, 260), y1(740, 260));
        g.drawLine(x1(660, 300), y1(660, 300), x1(780, 300), y1(780, 300));        

        g.drawLine(x1(660, 100), y1(660, 100), x1(660, 300), y1(660, 300));
        g.drawLine(x1(700, 140), y1(700, 140), x1(700, 180), y1(700, 180));
        g.drawLine(x1(700, 220), y1(700, 220), x1(700, 260), y1(700, 260));
        g.drawLine(x1(740, 140), y1(740, 140), x1(740, 180), y1(740, 180));
        g.drawLine(x1(740, 220), y1(740, 220), x1(740, 260), y1(740, 260));
        g.drawLine(x1(780, 100), y1(780, 100), x1(780, 300), y1(780, 300));

        //Membuat o
        g.drawLine(x1(800, 100), y1(800, 100), x1(920, 100), y1(920, 100));
        g.drawLine(x1(840, 140), y1(840, 140), x1(880, 140), y1(880, 140));
        g.drawLine(x1(840, 260), y1(840, 260), x1(880, 260), y1(880, 260));
        g.drawLine(x1(800, 300), y1(800, 300), x1(920, 300), y1(920, 300));        

        g.drawLine(x1(800, 100), y1(800, 100), x1(800, 300), y1(800, 300));
        g.drawLine(x1(840, 140), y1(840, 140), x1(840, 260), y1(840, 260));
        g.drawLine(x1(880, 140), y1(880, 140), x1(880, 260), y1(880, 260));
        g.drawLine(x1(920, 100), y1(920, 100), x1(920, 300), y1(920, 300));
     
        //Membuat G
//        g.drawLine(100+x, 100+y, 220+x, 100+y);
//        g.drawLine(140+x, 140+y, 220+x, 140+y);
//        g.drawLine(160+x, 180+y, 220+x, 180+y);
//        g.drawLine(160+x, 220+y, 180+x, 220+y);
//        g.drawLine(140+x, 260+y, 180+x, 260+y);
//        g.drawLine(100+x, 300+y, 220+x, 300+y);        
//
//        g.drawLine(100+x, 100+y, 100+x, 300+y);
//        g.drawLine(140+x, 140+y, 140+x, 260+y);
//        g.drawLine(160+x, 180+y, 160+x, 220+y);
//        g.drawLine(180+x, 220+y, 180+x, 260+y);
//        g.drawLine(220+x, 180+y, 220+x, 300+y);
//        g.drawLine(220+x, 100+y, 220+x, 140+y);
//        
//        //Membuat 5
//        g.drawLine(240+x, 100+y, 360+x, 100+y);
//        g.drawLine(280+x, 140+y, 360+x, 140+y);
//        g.drawLine(280+x, 180+y, 360+x, 180+y);
//        g.drawLine(240+x, 220+y, 320+x, 220+y);
//        g.drawLine(240+x, 260+y, 320+x, 260+y);
//        g.drawLine(240+x, 300+y, 360+x, 300+y);        
//
//        g.drawLine(240+x, 100+y, 240+x, 220+y);
//        g.drawLine(240+x, 260+y, 240+x, 300+y);
//        g.drawLine(280+x, 140+y, 280+x, 180+y);
//        g.drawLine(320+x, 220+y, 320+x, 260+y);
//        g.drawLine(360+x, 100+y, 360+x, 140+y);
//        g.drawLine(360+x, 180+y, 360+x, 300+y);
//        
//        //Membuat 7
//        g.drawLine(380+x, 100+y, 500+x, 100+y);
//        g.drawLine(380+x, 140+y, 460+x, 140+y);
//        g.drawLine(460+x, 300+y, 500+x, 300+y);        
//
//        g.drawLine(380+x, 100+y, 380+x, 140+y);
//        g.drawLine(460+x, 140+y, 460+x, 300+y);
//        g.drawLine(500+x, 100+y, 500+x, 300+y);
//
//        //Membuat 3
//        g.drawLine(520+x, 100+y, 640+x, 100+y);
//        g.drawLine(520+x, 140+y, 600+x, 140+y);
//        g.drawLine(520+x, 180+y, 600+x, 180+y);
//        g.drawLine(520+x, 220+y, 600+x, 220+y);
//        g.drawLine(520+x, 260+y, 600+x, 260+y);
//        g.drawLine(520+x, 300+y, 640+x, 300+y);        
//
//        g.drawLine(520+x, 100+y, 520+x, 140+y);
//        g.drawLine(520+x, 180+y, 520+x, 220+y);
//        g.drawLine(520+x, 260+y, 520+x, 300+y);
//        g.drawLine(600+x, 140+y, 600+x, 180+y);
//        g.drawLine(600+x, 220+y, 600+x, 260+y);
//        g.drawLine(640+x, 100+y, 640+x, 300+y);
//
//        //Membuat 8
//        g.drawLine(660+x, 100+y, 780+x, 100+y);
//        g.drawLine(700+x, 140+y, 740+x, 140+y);
//        g.drawLine(700+x, 180+y, 740+x, 180+y);
//        g.drawLine(700+x, 220+y, 740+x, 220+y);
//        g.drawLine(700+x, 260+y, 740+x, 260+y);
//        g.drawLine(660+x, 300+y, 780+x, 300+y);        
//
//        g.drawLine(660+x, 100+y, 660+x, 300+y);
//        g.drawLine(700+x, 140+y, 700+x, 180+y);
//        g.drawLine(700+x, 220+y, 700+x, 260+y);
//        g.drawLine(740+x, 140+y, 740+x, 180+y);
//        g.drawLine(740+x, 220+y, 740+x, 260+y);
//        g.drawLine(780+x, 100+y, 780+x, 300+y);
//
//        //Membuat o
//        g.drawLine(800+x, 100+y, 920+x, 100+y);
//        g.drawLine(840+x, 140+y, 880+x, 140+y);
//        g.drawLine(840+x, 260+y, 880+x, 260+y);
//        g.drawLine(800+x, 300+y, 920+x, 300+y);        
//
//        g.drawLine(800+x, 100+y, 800+x, 300+y);
//        g.drawLine(840+x, 140+y, 840+x, 260+y);
//        g.drawLine(880+x, 140+y, 880+x, 260+y);
//        g.drawLine(920+x, 100+y, 920+x, 300+y);
        
    }
}
