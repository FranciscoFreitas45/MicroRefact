package com.project.stockexchangeappbackend.repository.specification;
 import com.project.stockexchangeappbackend.entity.Transaction;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
@Join(path = "buyingOrder", alias = "o")
@Join(path = "o.stock", alias = "s")
@And({ @Spec(path = "date", spec = GreaterThanOrEqual.class, params = { "date>" }), @Spec(path = "date", spec = LessThanOrEqual.class, params = { "date<" }), @Spec(path = "amount", spec = GreaterThanOrEqual.class, params = { "amount>" }), @Spec(path = "amount", spec = LessThanOrEqual.class, params = { "amount<" }), @Spec(path = "amount", spec = Equal.class, params = { "amount" }), @Spec(path = "unitPrice", spec = GreaterThanOrEqual.class, params = { "unitPrice>" }), @Spec(path = "unitPrice", spec = LessThanOrEqual.class, params = { "unitPrice<" }), @Spec(path = "unitPrice", spec = Equal.class, params = { "unitPrice" }), @Spec(path = "s.name", spec = LikeIgnoreCase.class, params = { "name" }), @Spec(path = "s.abbreviation", spec = LikeIgnoreCase.class, params = { "abbreviation" }) })
public interface TransactionSpecification extends Specification<Transaction>{


}