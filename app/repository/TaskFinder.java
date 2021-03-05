package repository;

import io.ebean.Finder;
import io.ebean.ExpressionList;
import io.ebean.DB;
import java.util.List;
import java.util.Date;
import models.Task;


public class TaskFinder extends Finder<Long,Task> {

  public TaskFinder() {
    super(Task.class);
  }

  // Add finder methods ...

  public List<Task> byLabel(String label) {
    return query().where().istartsWith("label", label).findList();
  }

  public List<Task> byNowDate() {
    List<Task> tasks = DB.find(Task.class).where().lt("dateCreated", new Date()).findList();
    return tasks;
  }

  public List<Task> all() {
    return query()
      // .where()
      // .eq("status", Task.Status.NEW)
      .orderBy("label")
      .findList();
  }
}
