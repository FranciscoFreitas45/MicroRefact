package Interface;
public interface FormFilter {

   public String getFiltertype();
   public Object equals(Object Object);
   public String getTableid();
   public String getBatid();
   public String getId();
   public int getExecnum();
   public void setExecnum(int execnum);
   public String getName();
   public int getFilternum();
   public void setFilternum(int filternum);
}