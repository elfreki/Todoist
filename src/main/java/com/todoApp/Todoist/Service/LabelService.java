package com.todoApp.Todoist.Service;

import com.todoApp.Todoist.Repository.LabelRepository;
import com.todoApp.Todoist.enitity.Label;
import com.todoApp.Todoist.enitity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {

    private final LabelRepository LabelRepository;
    private final UserService userService;

    @Autowired
    public LabelService(LabelRepository LabelRepository, UserService userService) {
        this.LabelRepository = LabelRepository;
        this.userService = userService;
    }

    public Label createLabel(String userId, Label label) {
        if(userService.getUser(userId) != null){
            label.setUser(userService.getUser(userId));
        }
        return LabelRepository.save(label);
    }

    public List<Label> getAllLabels() {
        return LabelRepository.findAll();
    }

    public Label getLabel(String id) {
        return LabelRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Label not found with id " + id));
    }

    public Label updateLabel(Label label) {
        return LabelRepository.save(label);
    }

    public void deleteLabel(String id) {
        LabelRepository.deleteById(id);
    }

    public Label getLabelByNameAndColour(String title, String color){
        return LabelRepository.getLabelByTitleAndColor(title,color);
    }
}

