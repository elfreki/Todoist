package com.todoApp.Todoist.Repository;

import com.todoApp.Todoist.enitity.Label;
import com.todoApp.Todoist.enitity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, String> {
    Label getLabelByTitleAndColor(String title, String color);
}
