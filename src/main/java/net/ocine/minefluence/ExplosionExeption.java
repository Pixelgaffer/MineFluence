package net.ocine.minefluence;

/**
 * Created by florian on 30.10.14.
 */
public class ExplosionExeption extends UnsupportedOperationException {
    public ExplosionExeption() {
        super("EXPLOSION");
    }

    @Override
    public String getMessage() {
        String explosion = "EXPLOSION\n";
        for(int i = 0; i < 250; i++){
            explosion += "EXPLOSION\n";
        }
        return explosion;
    }
}
