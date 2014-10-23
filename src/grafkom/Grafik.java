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
