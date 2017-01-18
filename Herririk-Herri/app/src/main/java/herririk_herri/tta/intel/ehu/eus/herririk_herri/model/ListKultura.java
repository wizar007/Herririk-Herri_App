package herririk_herri.tta.intel.ehu.eus.herririk_herri.model;

import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikel on 18/01/17.
 */
public class ListKultura {
    private String kulturaCode;
    public List<Kultura> lkultura;
    private int size;

    public ListKultura()
    {
        this.lkultura=new ArrayList<Kultura>();
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Kultura> getLkultura() {
        return this.lkultura;
    }

    public void setLkultura(List<Kultura> lkultura) {
        this.lkultura = lkultura;
    }
    public String getKulturaCode() {
        return this.kulturaCode;
    }

    public void setKulturaCode(String kulturaCode) {
        this.kulturaCode = kulturaCode;
    }
    public void addChoice(Kultura kultura) {
        this.lkultura.add(kultura);
    }

}
