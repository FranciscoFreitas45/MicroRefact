package com.project.stockexchangeappbackend.repository.specification;
 import com.project.stockexchangeappbackend.entity.Resource;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
@Join(path = "user", alias = "u")
@And({ @Spec(path = "u.email", spec = LikeIgnoreCase.class, params = { "email" }), @Spec(path = "u.firstName", spec = LikeIgnoreCase.class, params = { "firstName" }), @Spec(path = "u.lastName", spec = LikeIgnoreCase.class, params = { "lastName" }), @Spec(path = "u.money", spec = GreaterThanOrEqual.class, params = { "money>" }), @Spec(path = "u.money", spec = LessThanOrEqual.class, params = { "money<" }), @Spec(path = "u.money", spec = Equal.class, params = { "money" }), @Spec(path = "amount", spec = GreaterThanOrEqual.class, params = { "amount>" }), @Spec(path = "amount", spec = LessThanOrEqual.class, params = { "amount<" }), @Spec(path = "amount", spec = Equal.class, params = { "amount" }), @Spec(path = "u.isActive", spec = Equal.class, params = { "active" }) })
public interface OwnerSpecification extends Specification<Resource>{


}