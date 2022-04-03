package org.sdrc.Interface;
public interface CounterCountRepository {

   public CounterCount findTotalCount();
   public void save(CounterCount counterCount);
}