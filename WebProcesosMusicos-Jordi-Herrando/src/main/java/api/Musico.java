package api;

public class Musico {
    private String name;
    private double edad;
    private String banda;
    private int añosActivo;

    public Musico(String name, double edad, String banda, int añosActivo) {
        this.name = name;
        this.edad = edad;
        this.banda = banda;
        this.añosActivo = añosActivo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getedad() {
        return edad;
    }

    public void setedad(double edad) {
        this.edad = edad;
    }

    public String getbanda() {
        return banda;
    }

    public void setbanda(String banda) {
        this.banda = banda;
    }

    public int getañosActivo() {
        return añosActivo;
    }

    public void setañosActivo(int añosActivo) {
        this.añosActivo = añosActivo;
    }

    @Override
    public String toString() {
        return "musico{" +
                "name='" + name + '\'' +
                ", edad=" + edad +
                ", banda='" + banda + '\'' +
                ", añosActivo=" + añosActivo +
                '}';
    }
}