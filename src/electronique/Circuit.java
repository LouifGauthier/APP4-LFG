package electronique;

import java.util.List;

public abstract class Circuit extends Composant{
    protected List<Composant> composants;

    public Circuit(List<Composant> composants){
        setComposants(composants);
    }

    public List<Composant> getComposants() {
        return composants;
    }

    public void setComposants(List<Composant> composants) {
        this.composants = composants;
    }
}
