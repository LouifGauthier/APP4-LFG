package electronique;

import java.util.ArrayList;
import java.util.List;

public class CircuitSerie extends Circuit{

    public CircuitSerie(List<Composant> composant){
    super(composant);


    }
    public double calculerResistance() {
        double resistance = 0;
        for (Composant composant : composants){
            resistance += composant.calculerResistance();
        }
        return resistance;
    }
}
