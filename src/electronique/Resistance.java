package electronique;

public class Resistance extends Composant{
    private double resistance;
    public Resistance(double resistance){
        this.resistance = resistance;
    }

    public double calculerResistance() {
        return resistance;
    }
}
