//package com.todoApp.Todoist.vo;
//
//import com.todoApp.Todoist.enitity.Label;
//import com.todoApp.Todoist.enitity.Project;
//import com.todoApp.Todoist.enitity.Task;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public class FilterTaskVo {
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public class TaskRequestVo {
//        private String title;
//
//        private String description;
//
//        private LocalDate dueDate;
//
//        private String status;
//
//        private Project project;
//
//        private List<Label> labels;
//        public Task toTask(){
//            Task task = new Task();
//            task.setTitle(this.title);
//            task.setDescription(this.description);
//            task.setDueDate(this.dueDate);
//            task.setStatus(this.status);
//            return task;
//        }
//}
