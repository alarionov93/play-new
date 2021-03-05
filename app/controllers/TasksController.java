package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
// import play.data.Form;
import play.i18n.*;
import javax.inject.Inject;

import models.*;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class TasksController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */

    private final Form<Task> taskForm;
    private MessagesApi messagesApi;

    @Inject
    public TasksController(FormFactory formFactory, MessagesApi messagesApi) {
        this.taskForm = formFactory.form(Task.class);        
        this.messagesApi = messagesApi;
    }

    public Result tasks(Http.Request request) {
        return ok(views.html.tasks.render(Task.all(), taskForm, request, messagesApi.preferred(request)));
    }

    public Result tasksByLabel(Http.Request request, String label) {
        return ok(views.html.tasks.render(Task.byLabel(label), taskForm, request, messagesApi.preferred(request)));
    }

    public Result tasksByNowDate(Http.Request request) {
        return ok(views.html.tasks.render(Task.byNowDate(), taskForm, request, messagesApi.preferred(request)));
    }

    public Result newTask(Http.Request request) {
        final Form<Task> filledForm = taskForm.bindFromRequest(request);
        if(filledForm.hasErrors()) {
          return badRequest(
            views.html.tasks.render(Task.all(), filledForm, request, messagesApi.preferred(request))
          );
        } else {
          Task.create(filledForm.get());
          return redirect(routes.TasksController.tasks());  
        }
    }
    
    public Result deleteTask(Long id) {
        // final Form<Task> filledForm = taskForm.bindFromRequest(request);
        Task.delete(id);
        return redirect(routes.TasksController.tasks());  
    }

    public Result jsonTasks() {

        return ok(Task.byDateJson());
    }

}
