package cn.com.cnc.fcc.Interface;
public interface PageUtil {

   public Page<T> listToPage(List<T> list,Pageable pageable);
}