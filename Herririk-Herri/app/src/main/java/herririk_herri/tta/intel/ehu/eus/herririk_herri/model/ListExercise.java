package herririk_herri.tta.intel.ehu.eus.herririk_herri.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikel on 18/01/17.
 */
public class ListExercise {

    public List<Exercise> lExercise;
    private int size;
    private String lessonCode;

    public ListExercise() {
        this.lExercise = new ArrayList<Exercise>();
    }

    public ListExercise(String lessonCode) {
        this.setLessonCode(lessonCode);

    }

    public String getLessonCode() {
        return this.lessonCode;
    }

    public void setLessonCode(String lessonCode) {
        this.lessonCode = lessonCode;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Exercise> getExercises() {
        return this.lExercise;
    }

    public void setExercises(List<Exercise> exercises) {
        this.lExercise = exercises;
    }

    public void addChoice(Exercise exercise) {
        this.lExercise.add(exercise);
    }
}
