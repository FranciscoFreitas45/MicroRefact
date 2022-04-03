package Interface;
public interface CallOutFilter {

   public void setName(String name);
   public void setExecnum(int execnum);
   public void setAssigned(int assigned);
   public void setAssignedorgan(int assignedorgan);
   public void setAssignedai(int assignedai);
   public void setAssignedforecast(int assignedforecast);
   public void setNotassigned(int notassigned);
   public void setRenum(int renum);
   public void setReorgannum(int reorgannum);
   public String getId();
   public int getAssigned();
   public int getNotassigned();
   public int getAssignedorgan();
   public int getReorgannum();
   public int getAssignedai();
   public int getAssignedforecast();
   public int getRenum();
}