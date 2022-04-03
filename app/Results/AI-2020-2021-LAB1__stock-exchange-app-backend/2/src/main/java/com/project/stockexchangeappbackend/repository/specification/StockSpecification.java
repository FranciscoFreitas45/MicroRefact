package com.project.stockexchangeappbackend.repository.specification;
 import com.project.stockexchangeappbackend.entity.Stock;
import net.kaczmarzyk.spring.data.jpa.domain;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
@Join(path = "tag", alias = "t")
@And({ @Spec(path = "name", spec = LikeIgnoreCase.class), @Spec(path = "abbreviation", spec = LikeIgnoreCase.class), @Spec(path = "currentPrice", spec = GreaterThanOrEqual.class, params = { "currentPrice>" }), @Spec(path = "currentPrice", spec = LessThanOrEqual.class, params = { "currentPrice<" }), @Spec(path = "currentPrice", spec = Equal.class, params = { "currentPrice" }), @Spec(path = "amount", spec = GreaterThanOrEqual.class, params = { "amount>" }), @Spec(path = "amount", spec = LessThanOrEqual.class, params = { "amount<" }), @Spec(path = "amount", spec = Equal.class, params = { "amount" }), @Spec(path = "priceChangeRatio", spec = GreaterThanOrEqual.class, params = "priceChangeRatio>"), @Spec(path = "priceChangeRatio", spec = LessThanOrEqual.class, params = "priceChangeRatio<"), @Spec(path = "t.name", spec = EqualIgnoreCase.class, params = { "tag" }) })
public interface StockSpecification extends Specification<Stock>{


}