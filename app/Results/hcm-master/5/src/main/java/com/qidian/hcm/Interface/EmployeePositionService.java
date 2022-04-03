package com.qidian.hcm.Interface;
public interface EmployeePositionService {

   public Map<Long,EmployeePosition> getEmployeeCurrentPositionMap(List<Long> employeeIds);
   public List<EmployeePosition> findAllByCondition(Specification<EmployeePosition> condition);
   public List<EmployeePosition> findAllByExcludeEmployeeStatus(EmployeeStatus status);
   public List<EmployeePositionDTO> getEmployeePositionDTOList(Long employeeId);
   public void saveEmployeePositions(Long employeeId,List<EmployeePositionDTO> employeePositionDTOList);
   public void addEmployeePositionForTransferredEmployee(Long employeeId,TransferEmployeeRequest transferEmployeeRequest);
   public void updateLeaderId(Long employeeId,Long handoverManId);
   public EmployeePosition getCurrentEmployeePosition(Long employeeId);
}