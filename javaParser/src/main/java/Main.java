import com.github.javaparser.ast.CompilationUnit;

import graph.entities.MyClassDTO;
import parser.Parse;
import parser.Parser;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        parseProject(args[0]);

    }

    public static Map<String, MyClassDTO> parseProject(String projectPath) throws IOException {
        Parser parser = new Parser();
        List<CompilationUnit> compilationUnits = parser.parseProject(Path.of(projectPath));
        //System.out.println( "%%%%%%%%% "+compilationUnits); // cada CompilationUnit é uma classe de java

        // TODO : Consider merging Parse with Parser
        Parse parse = new Parse();
        return parse.completeParse(compilationUnits);
    }






}


