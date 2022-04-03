package com.gs.Interface;
public interface IncomingOutgoingService {

   public List<IncomingOutgoing> queryByCompanyIdForInType(String companyId);
   public List<IncomingOutgoing> queryByCompanyIdForOutType(String companyId);
}