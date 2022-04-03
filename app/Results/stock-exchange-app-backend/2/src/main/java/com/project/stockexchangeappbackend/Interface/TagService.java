package com.project.stockexchangeappbackend.Interface;
import java.util.*;
import com.project.stockexchangeappbackend.DTO.Tag;
public interface TagService {

   public Optional<Tag> getTag(String name);
}