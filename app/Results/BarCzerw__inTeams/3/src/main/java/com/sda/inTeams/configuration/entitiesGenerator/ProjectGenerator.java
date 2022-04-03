package com.sda.inTeams.configuration.entitiesGenerator;
 import com.sda.inTeams.configuration.StringUtilities;
import com.sda.inTeams.model.Project.Project;
import com.sda.inTeams.model.Project.ProjectStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
public class ProjectGenerator {

 private  Random random;


public List<Project> generateProjects(int size){
    List<Project> projects = new ArrayList<>();
    IntStream.range(0, size).forEach(ind -> projects.add(generateSingleProject()));
    return projects;
}


public Project generateSingleProject(){
    return Project.builder().name("Project " + StringUtilities.getRandomWord() + " " + StringUtilities.getRandomNumberAsString(3)).status(generateProjectStatus()).build();
}


public ProjectStatus generateProjectStatus(){
    List<ProjectStatus> projectStatuses = new ArrayList<>(List.of(ProjectStatus.values()));
    return projectStatuses.get(random.nextInt(projectStatuses.size()));
}


}