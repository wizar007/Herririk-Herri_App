package herririk_herri.tta.intel.ehu.eus.herririk_herri.model;

/**
 * Created by mikel on 26/01/17.
 */
public class Test {

    private String enunciado;

    private String opcion_1;

    private String opcion_2;

    private String opcion_3;

    private int solucion;

    public Test(String enunciado, String opcion_1, String opcion_2, String opcion_3,int solucion)
    {
        this.setEnunciado(enunciado);
        this.setOpcion_1(opcion_1);
        this.setOpcion_2(opcion_2);
        this.setOpcion_3(opcion_3);
        this.setSolucion(solucion);
    }
    public Test()
    {
        
    }

    public String getEnunciado() {
        return this.enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getOpcion_1() {
        return this.opcion_1;
    }

    public void setOpcion_1(String opcion_1) {
        this.opcion_1 = opcion_1;
    }

    public String getOpcion_2() {
        return this.opcion_2;
    }

    public void setOpcion_2(String opcion_2) {
        this.opcion_2 = opcion_2;
    }

    public String getOpcion_3() {
        return this.opcion_3;
    }

    public void setOpcion_3(String opcion_3) {
        this.opcion_3 = opcion_3;
    }

    public int getSolucion() {
        return this.solucion;
    }

    public void setSolucion(int solucion) {
        this.solucion = solucion;
    }
}
