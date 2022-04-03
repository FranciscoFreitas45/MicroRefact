package com.byr.warehouse.Interface;
public interface RelationShipRepository {

   public List<RelationShip> findRelationShipsBysupplyName(String supplyName);
}