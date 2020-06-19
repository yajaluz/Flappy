package flappy;

import Interface.Acao;
import Interface.Jogo;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Yasmin
 */
public class Flappy implements Jogo{

    /**
     * @param args the command line arguments
     */
    
    public double ground_offset = 0;
    public double groundVelocX = 50;
    public ObjFly obj;
    public ArrayList<Cano> canos = new ArrayList<>();
    public Random rand = new Random();
    public Timer timeCano;

    public Flappy() {
        obj = new ObjFly(35,(getLargura()-112)/2);
//        canos.add(new Cano(getLargura()+10, rand.nextInt(getAltura() - 112 - Cano.holisize ), -groundVelocX));
        timeCano = new Timer(5, true, addCano());
        addCano().executa();
    }
    
    private Acao addCano(){
        return new Acao(){
            @Override
            public void executa(){
                canos.add(new Cano(getLargura()+10, rand.nextInt(getAltura() - 112 - Cano.holisize ), -groundVelocX));
            }
        };
    }
    
    public static void main(String[] args) {
       roda();
    }

    private static void roda() {
        new Motor(new Flappy());
    }

    @Override
    public String getTitulo() {
       return "Flappy Object";
    }

    @Override
    public int getLargura() {
       return 384;
    }

    @Override
    public int getAltura() {
       return 512;
    }

    @Override
    public void tique(Set<String> teclas, double dt) {//movimrnto
        ground_offset += dt * groundVelocX;
        ground_offset = ground_offset % 308;
        
        timeCano.tique(dt);
        
        obj.atualiza(dt);
        
        if(obj.y+24 >= getAltura()-112)
            System.out.println("perdeu, playboy");
        else if (obj.y <= 0)
            System.out.println("perdeu, playboy"); 
        
         for (Cano cano : canos){
           cano.atualiza(dt);
           
           if(obj.box.intersecao(cano.boxCima) != 0 || obj.box.intersecao(cano.boxBaixo) != 0)
               System.out.println("perdeu, playboy");
         }
         
         if (canos.size() > 0 && canos.get(0).x < - 60)
            canos.remove(0);
         
         
    }

    @Override
    public void tecla(String tecla) {
        if(tecla.equals(" "))
            obj.flap();
    }

    @Override
    public void desenhar(Tela tela) { //apresentar na tela
        //background
       tela.imagem("C:\\Users\\Yasmin\\Documents\\NetBeansProjects\\Flappy\\src\\Img\\flappy.png", 0, 0, 288, 512, 0, 0, 0);
       tela.imagem("C:\\Users\\Yasmin\\Documents\\NetBeansProjects\\Flappy\\src\\Img\\flappy.png", 0, 0, 288, 512, 0, 288, 0);
//       tela.imagem("C:\\Users\\Yasmin\\Documents\\NetBeansProjects\\Flappy\\src\\Img\\flappy.png", 0, 0, 288, 512, 0, 288*2, 0);;
       
       for (Cano cano : canos){
           cano.desenha(tela);
       }
       
       //ground
       tela.imagem("C:\\Users\\Yasmin\\Documents\\NetBeansProjects\\Flappy\\src\\Img\\flappy.png", 292, 0, 308, 112, 0, -ground_offset, getAltura()-112);
       tela.imagem("C:\\Users\\Yasmin\\Documents\\NetBeansProjects\\Flappy\\src\\Img\\flappy.png", 292, 0, 308, 112, 0, 308 - ground_offset, getAltura()-112);
       tela.imagem("C:\\Users\\Yasmin\\Documents\\NetBeansProjects\\Flappy\\src\\Img\\flappy.png", 292, 0, 308, 112, 0, 308 * 2 - ground_offset, getAltura() - 112);
       
       obj.desenhar(tela);
       
       
    }



    
}
