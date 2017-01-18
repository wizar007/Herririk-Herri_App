package herririk_herri.tta.intel.ehu.eus.herririk_herri.model;

/**
 * Created by mikel on 18/01/17.
 */
public class Kultura {
    private String img;
    private String informacion;
    private String kulturaType;

    public Kultura(String img,String informacion,String kulturaType)
    {
        this.setImg(img);
        this.setInformacion(informacion);
        this.setKulturaType(kulturaType);

    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getInformacion() {
        return this.informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getKulturaType() {
        return this.kulturaType;
    }

    public void setKulturaType(String kulturaType) {
        this.kulturaType = kulturaType;
    }

}
