package flappy;

/**
 *
 * @author Yasmin
 */
public class ObjFly {
  
    public double x, y;
    public double velocY = 0;
    public static final double G = 800;
    public static final double FLAP = -300;
    public Hitbox box;

    public ObjFly(double x, double y) {
        this.x = x;
        this.y = y;
        this.box = new Hitbox(x, y, x+34, y+24);
    }
    
    public void atualiza(double dt){
        velocY += G*dt;
        y += velocY*dt;
        
        box.mover(0, velocY*dt);
    }
    
    public void flap(){
        velocY = FLAP;
    }
    
    public void desenhar(Tela tela){
        tela.imagem("C:\\Users\\Yasmin\\Documents\\NetBeansProjects\\Flappy\\src\\Img\\flappy.png", 528, 128, 34, 24, Math.atan(velocY/500), x, y);
        
    }
   
}
