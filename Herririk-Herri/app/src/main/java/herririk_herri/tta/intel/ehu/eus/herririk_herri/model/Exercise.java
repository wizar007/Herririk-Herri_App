package herririk_herri.tta.intel.ehu.eus.herririk_herri.model;

/**
 * Created by mikel on 18/01/17.
 */
public class Exercise {
    private String ariketaKode;

    private String audio;


    private String enunciado;


    private String img;


    private String opcion_1;


    private String opcion_2;


    private String opcion_3;


    private int solucion;


    private String tipo;
    public Exercise()
    {

    }

    public Exercise(String ariketaKode, String audio, String enunciado,String img, String opcion_1, String opcion_2,String opcion_3,int solucion, String tipo)
    {
        this.ariketaKode=ariketaKode;
        this.audio=audio;
        this.enunciado=enunciado;
        this.img=img;
        this.opcion_1=opcion_1;
        this.opcion_2=opcion_2;
        this.opcion_3=opcion_3;
        this.solucion=solucion;
        this.tipo=tipo;
    }



    public String getAriketaKode() {
        return this.ariketaKode;
    }

    public void setAriketaKode(String ariketaKode) {
        this.ariketaKode = ariketaKode;
    }

    public String getAudio() {
        return this.audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getEnunciado() {
        return this.enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
