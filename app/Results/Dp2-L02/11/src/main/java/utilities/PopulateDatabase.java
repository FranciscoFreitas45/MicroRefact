package utilities;
 import utilities.internal.DatabasePopulator;
public class PopulateDatabase {


public void main(String[] args){
    DatabasePopulator.run("PopulateDatabase 1.18.2", "classpath:PopulateDatabase.xml");
}


}