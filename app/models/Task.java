package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import repository.TaskFinder;
import repository.TaskFinderJson;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import play.data.validation.Constraints.*;
import java.util.*;

@Entity
public class Task extends Printed {
    
    @Id
    public Long id;

    @Required
    public String label;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static final TaskFinder finder = new TaskFinder();
    public static final TaskFinderJson jsonFinder = new TaskFinderJson();
    
    public static List<Task> all() {
        return finder.all();
    }

    public static List<Task> byLabel(String label) {
        return finder.byLabel(label);
    }

    public static List<Task> byNowDate() {
        return finder.byNowDate();
    }
    
    public static void create(Task task) {
        task.save();
    }
    
    public static void delete(Long id) {
        finder.ref(id).delete();
    }
    public static String byDateJson() {
        return jsonFinder.byDate();
    }
    
}