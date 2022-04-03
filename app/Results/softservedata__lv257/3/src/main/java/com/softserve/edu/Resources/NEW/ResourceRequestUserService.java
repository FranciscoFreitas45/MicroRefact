package com.softserve.edu.Resources.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.dao.impl.ResourceRequestDAOImpl;
import com.softserve.edu.Resources.entity.ResourceRequest;
@Service
public class ResourceRequestUserService {

@Autowired
 private ResourceRequestDAOImpl resourcerequestdaoimpl;


}