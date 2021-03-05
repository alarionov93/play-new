// package repository;

// import io.ebean.Ebean;
// import io.ebean.EbeanServer;
// import models.Company;
// import play.db.ebean.EbeanConfig;

// import javax.inject.Inject;
// import java.util.HashMap;
// import java.util.LinkedHashMap;
// import java.util.Map;
// import java.util.concurrent.CompletionStage;

// import static java.util.concurrent.CompletableFuture.supplyAsync;

// /**
//  *
//  */
// public class TaskRepository {

//     private final EbeanServer ebeanServer;
//     private final DatabaseExecutionContext executionContext;

//     @Inject
//     public TaskRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext) {
//         this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
//         this.executionContext = executionContext;
//     }

//     public CompletionStage<Map<String, String>> all() {
//         return supplyAsync(() -> ebeanServer.find(Task.class).orderBy("label").findList(), executionContext)
//                 .thenApply(list -> {
//                     HashMap<String, String> tasks = new LinkedHashMap<String, String>();
//                     for (Task c : list) {
//                         tasks.put(c.id.toString(), c.label);
//                     }
//                     return tasks;
//                 });
//     }

// }