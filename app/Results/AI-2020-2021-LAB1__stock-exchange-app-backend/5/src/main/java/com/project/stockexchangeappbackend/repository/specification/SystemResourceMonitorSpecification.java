package com.project.stockexchangeappbackend.repository.specification;
 import com.project.stockexchangeappbackend.entity.SystemResourcesMonitor;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
@And({ @Spec(path = "timestamp", spec = GreaterThanOrEqual.class, params = { "datetime>" }), @Spec(path = "timestamp", spec = LessThanOrEqual.class, params = { "datetime<" }) })
public interface SystemResourceMonitorSpecification extends Specification<SystemResourcesMonitor>{


}