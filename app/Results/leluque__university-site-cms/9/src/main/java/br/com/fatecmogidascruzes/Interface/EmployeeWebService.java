package br.com.fatecmogidascruzes.Interface;
public interface EmployeeWebService {

   public List<EmployeeEntryDTO> getEnabledByCourse(Course course);
   public List<EmployeeEntryDTO> getEnabledProfessors();
}