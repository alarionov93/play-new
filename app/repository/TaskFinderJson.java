package repository;

import io.ebean.Finder;
import io.ebean.ExpressionList;
import io.ebean.DB;
import java.util.List;
import java.util.Date;
import models.Task;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TaskFinderJson extends Finder<Long,Task> {

  public TaskFinderJson() {
    super(Task.class);
  }

  public String byDate() {
    List<Task> tasks = DB.find(Task.class).where().orderBy("dateCreated asc").findList();
    ObjectMapper om = new ObjectMapper();
    String resJson = "";
    try {
      resJson = om.writeValueAsString(tasks);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return resJson;
  }

  // Add finder methods ...

  // public List<Task> byLabel(String label) {
  //   return query().where().istartsWith("label", label).findList();
  // }

  // public List<Task> byNowDate() {
  //   List<Task> tasks = DB.find(Task.class).where().lt("dateCreated", new Date()).findList();
  //   return tasks;
  // }

  // public List<Task> all() {
  //   return query()
  //     // .where()
  //     // .eq("status", Task.Status.NEW)
  //     .orderBy("label")
  //     .findList();
  // }
}
