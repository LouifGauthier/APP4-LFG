package electronique;


import java.util.ArrayList;
import java.util.List;

public class CircuitParallele extends Circuit {
    public CircuitParallele(List<Composant> composants) {
        super(composants);
        }

    public double calculerResistance() {
        double resistance = 0;
        for (Composant composant : composants){
            resistance += (1.0/composant.calculerResistance());
        }
        return 1/resistance;

    }
}
