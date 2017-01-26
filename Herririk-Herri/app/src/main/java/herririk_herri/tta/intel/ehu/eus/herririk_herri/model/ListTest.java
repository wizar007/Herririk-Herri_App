package herririk_herri.tta.intel.ehu.eus.herririk_herri.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikel on 26/01/17.
 */
public class ListTest {
    public List<Test> lTest;
    private int size;
    private String testCode;

    public ListTest() {
        this.lTest = new ArrayList<Test>();
    }

    public ListTest(String testCode) {
        this.setTestCode(testCode);

    }

    public String getTestCode() {
        return this.testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Test> getExercises() {
        return this.lTest;
    }

    public void setExercises(List<Test>  tests) {
        this.lTest = tests;
    }

    public void addChoice(Test test) {
        this.lTest.add(test);
    }


}
