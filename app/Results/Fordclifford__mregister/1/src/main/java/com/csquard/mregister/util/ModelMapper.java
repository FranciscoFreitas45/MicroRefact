package com.csquard.mregister.util;
 import com.csquard.mregister.model.Agent;
import com.csquard.mregister.model.User;
import com.csquard.mregister.payload.AgentResponse;
import com.csquard.mregister.payload.UserSummary;
public class ModelMapper {


public AgentResponse mapAgentToResponse(Agent agent,User creator){
    AgentResponse agentResponse = new AgentResponse();
    agentResponse.setAgent_no(agent.getAgent_no());
    agentResponse.setAddress(agent.getAddress());
    agentResponse.setDevice_type(agent.getDevice_type());
    agentResponse.setId_attachment(agent.getId_attachment());
    agentResponse.setId_no(agent.getId_no());
    agentResponse.setImei_no(agent.getImei_no());
    agentResponse.setLocation(agent.getLocation());
    agentResponse.setMobile(agent.getMobile());
    agentResponse.setSignature(agent.getSignature());
    agentResponse.setSigned_contact(agent.getSigned_contact());
    agentResponse.setSigned_name(agent.getSigned_name());
    agentResponse.setTdr_id(agent.getTdrId());
    agentResponse.setTown(agent.getTown());
    agentResponse.setSales_area_id(agent.getSalesAreaId());
    agentResponse.setSales_region_id(agent.getSalesRegionId());
    UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
    agentResponse.setCreatedBy(creatorSummary);
    return agentResponse;
}


}