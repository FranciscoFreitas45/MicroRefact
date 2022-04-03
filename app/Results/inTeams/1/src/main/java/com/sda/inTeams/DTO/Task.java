package com.sda.inTeams.DTO;
import com.sda.inTeams.model.Indexable;

import lombok.*;
import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Indexable{

 private Long id;

 private  String description;

 private  TaskStatus status;

 private  Project project;

 private  User userResponsible;

 private  Set<Comment> comments;





}