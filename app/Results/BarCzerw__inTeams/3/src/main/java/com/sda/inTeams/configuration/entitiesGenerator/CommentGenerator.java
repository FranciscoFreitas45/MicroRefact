package com.sda.inTeams.configuration.entitiesGenerator;
 import com.sda.inTeams.configuration.StringUtilities;
import com.sda.inTeams.model.Comment.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
public class CommentGenerator {


public List<Comment> generateComments(int size){
    List<Comment> comments = new ArrayList<>();
    IntStream.range(0, size).forEach(ind -> comments.add(generateSingleComment()));
    return comments;
}


public Comment generateSingleComment(){
    return Comment.builder().text(StringUtilities.getRandomTextString()).build();
}


}