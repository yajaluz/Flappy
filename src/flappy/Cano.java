package flappy;

/**
 *
 * @author Yasmin
 */
public class Cano {
    public double x;
    public double y;
    public double velocXCano;
    public static int holisize = 120;
    
    public Hitbox boxCima, boxBaixo;
    
    public Cano(double x, double y, double velocXCano){
       this.velocXCano = velocXCano;
       this.x = x;
       this.y = y;
       
       boxCima = new Hitbox(x, y-270,x+52, y);
       boxBaixo = new Hitbox(x, y+holisize,x+52, y+holisize+242);
    }
    
    public void atualiza(double dt){
        x += velocXCano*dt;
        
        boxCima.mover(velocXCano*dt, 0);
        boxBaixo.mover(velocXCano*dt, 0);
    }
    
    public void desenha(Tela tela){
        //superior
        tela.imagem("C:\\Users\\Yasmin\\Documents\\NetBeansProjects\\Flappy\\src\\Img\\flappy.png", 604, 0, 52, 270, 0, x, y-270);
        
        ///inferior
        tela.imagem("C:\\Users\\Yasmin\\Documents\\NetBeansProjects\\Flappy\\src\\Img\\flappy.png", 660, 0, 52, 242, 0, x, y+holisize);
    }
    
}
