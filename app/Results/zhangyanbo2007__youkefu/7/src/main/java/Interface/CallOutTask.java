package Interface;
public interface CallOutTask {

   public void setName(String name);
   public void setBatid(String batid);
   public void setOrgi(String orgi);
   public void setExectype(String exectype);
   public void setFilterid(String filterid);
   public void setActid(String actid);
   public void setExecnum(int execnum);
   public void setOrgan(String organ);
   public void setCreatetime(Date createtime);
   public void setNamenum(int namenum);
   public void setNotassigned(int notassigned);
   public void setAssigned(int assigned);
   public void setAssignedorgan(int assignedorgan);
   public void setAssignedai(int assignedai);
   public void setAssignedforecast(int assignedforecast);
   public int getNamenum();
   public void setRenum(int renum);
   public void setReorgannum(int reorgannum);
   public String getId();
   public int getAssigned();
   public int getNotassigned();
   public int getAssignedorgan();
   public int getAssignedai();
   public int getAssignedforecast();
   public int getRenum();
   public int getReorgannum();
}